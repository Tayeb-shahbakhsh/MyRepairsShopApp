package com.example.repaitshopapplication.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.repaitshopapplication.R
import com.example.repaitshopapplication.data.Product
import com.example.repaitshopapplication.data.ProductDate
import com.example.repaitshopapplication.data.ProductTime
import com.example.repaitshopapplication.databinding.ActivityMainBinding
import com.example.repaitshopapplication.databinding.DialogCreateProductBinding
import com.example.repaitshopapplication.utils.Issues
import com.example.repaitshopapplication.utils.Status
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import saman.zamani.persiandate.PersianDate


class MainActivity : AppCompatActivity(),  KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityMainBottomNavigationView.background = null
        setupNavController()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.fab.setOnClickListener {
            createProductDialog()
        }
    }

    private fun setupNavController() {
        val navController: NavController = Navigation.findNavController(
            this,
            R.id.navHostFragmentContainer
        )
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        setupWithNavController(bottomNavigationView, navController)
    }

    private fun createProductDialog() {
        val view = DialogCreateProductBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(view.root)
            .setCancelable(false)
            .show()

        view.problemAc.setAdapter( ArrayAdapter(this,R.layout.problem_item_list,Issues.ISSUES))
        view.problemCountNumberPicker.minValue = 1
        view.problemCountNumberPicker.maxValue = 50
        viewModel.photoPathLiveData.observe(this){
            if (it.isNotEmpty()){
                view.productIv.setImageURI(it.toUri())
            }
        }

        view.dateBtn.setOnClickListener {
            getDateFromDateDialog()
        }
        view.productIv.setOnClickListener {
            showSelectPhotoDialog()
        }
        view.saveBtn.setOnClickListener {
            saveProduct(dialog, view)
        }
    }

    private fun setupPhotoDialog(): PickSetup {
        return PickSetup().apply {
            buttonOrientation = LinearLayout.HORIZONTAL
            isSystemDialog = false
            galleryButtonText = getString(R.string.galleyTxt)
            cameraButtonText = getString(R.string.cameraTxt)
            width = 500
            height = 500
        }
    }

    private fun showSelectPhotoDialog() {
            PickImageDialog.build(setupPhotoDialog()) {
                if (it.error == null) {
                    viewModel.photoPathLiveData.postValue(it.path)
                }
            }.show(this)
    }

    private fun getDateFromDateDialog(){
        val dateDialog = PersianDatePickerDialog(this)
            .setPositiveButtonString("ثبت")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1398)
            .setCancelable(false)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
            .setActionTextColor(Color.GRAY)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : PersianPickerListener {
                override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                        viewModel.newProductDateLiveData.postValue(
                            ProductDate(
                                persianPickerDate.persianYear,
                                persianPickerDate.persianMonth,
                                persianPickerDate.persianDay
                            )
                        )
                }
                override fun onDismissed() {
                    Toast.makeText(this@MainActivity, "تاریخی انتخاب نکردید", Toast.LENGTH_LONG)
                        .show()
                }
            })
        dateDialog.show()
    }

    private fun saveProduct(dialog: AlertDialog, view: DialogCreateProductBinding) {
        val newProduct = getNewProductFromDialog(view, dialog)
        viewModel.addProducts(newProduct)
    }

    private fun getNewProductFromDialog(
        view: DialogCreateProductBinding,
        dialog: AlertDialog
    ): Product {
        dialog.hide()
        val name = view.nameET.text.toString()
        val num = view.numberET.text.toString()
        val date = viewModel.newProductDateLiveData.value!!
        val time = getTime()
        val status = Status.NOTREADY
        return Product(name = name, number = num, date = date, time = time, status = status)
    }

    private fun getTime(): ProductTime {
        val persianDate = PersianDate()
        return ProductTime(
            persianDate.hour.toString(), persianDate.minute.toString()
        )
    }
}
