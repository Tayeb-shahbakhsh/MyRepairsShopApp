package com.example.repaitshopapplication.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
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
import com.google.android.material.textview.MaterialTextView
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import saman.zamani.persiandate.PersianDate


class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityMainBottomNavigationView.background = null
        setupNavController()
        setUpFabClickListeners()
    }

    private fun setUpFabClickListeners() {
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

        view.problemAc.setAdapter(ArrayAdapter(this, R.layout.problem_item_list, Issues.ISSUES))
        view.problemCountNumberPicker.minValue = 1
        view.problemCountNumberPicker.maxValue = 50
        viewModel.newPhotoPathLiveData.observe(this) {
            if (it.isNotEmpty()) {
                view.productIv.setImageURI(it.toUri())
            }
        }

        setupDialogClickListeners(view, dialog)
    }

    private fun setupDialogClickListeners(view: DialogCreateProductBinding, dialog: AlertDialog) {
        view.problemBtn.setOnClickListener {
            var problemBadgeView = MaterialTextView(this)
            problemBadgeView.setBackgroundColor(resources.getColor(R.color.warning, null))
            problemBadgeView.text = makeProblemText(view)
            view.problemsContainerGridLayout.addView(problemBadgeView)
            viewModel.newProblemsLiveData.value?.add(makeProblemText(view))
        }
        view.dateBtn.setOnClickListener {
            getDateFromDateDialog()
        }
        view.productIv.setOnClickListener {
            showSelectPhotoDialog()
        }
        view.saveBtn.setOnClickListener {
            saveProduct(view)
            dialog.dismiss()
        }
    }

    fun makeProblemText(view: DialogCreateProductBinding): String =
        "  ${view.problemCountNumberPicker.value} * ${view.problemAc.text}  "

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
                viewModel.newPhotoPathLiveData.postValue(it.path)
            }
        }.show(this)
    }

    private fun getDateFromDateDialog() {
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

                }
            })
        dateDialog.show()
    }

    private fun saveProduct(view: DialogCreateProductBinding) {
        val newProduct = getNewProductFromDialog(view)
        viewModel.addProducts(newProduct)
    }

    private fun getNewProductFromDialog(
        view: DialogCreateProductBinding,
    ): Product {
        val name = view.nameET.text.toString()
        val num = view.numberET.text.toString()
        val date = viewModel.newProductDateLiveData.value!!
        val time = getTime()
        val photoPath = viewModel.newPhotoPathLiveData.value
        val problems = viewModel.newProblemsLiveData.value!!.toList()
        return Product(
            name = name,
            number = num,
            date = date,
            time = time,
            imagePath = photoPath!!,
            problems = problems,
        )
    }

    private fun getTime(): ProductTime {
        val persianDate = PersianDate()
        return ProductTime(
            persianDate.hour.toString(), persianDate.minute.toString()
        )
    }

}
