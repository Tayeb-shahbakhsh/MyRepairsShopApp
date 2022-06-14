package com.example.repaitshopapplication.utils

import android.graphics.Color

object Status {
    val READY = "آماده"
    val NOTREADY = "آماده نشده"
    val READYCOLOR = Color.GREEN
    val NOTREADYCOLOR = Color.RED
}

object Issues {
    val ISSUES = mutableListOf<String>(
        "سرزیپ",
        "زیپ کامل",
        "دوخت",
        "دوخت داخل",
        "دوخت بیرون",
        "دوخت زیپ",
        "دوخت آستر",
        "دوخت بند",
        "روفو",
        "حلقه",
        "گیره",
        "پرچ",
        "پین",
        "تعمیر تراولی",
        "تعمیر دسته",
        "تعویض تراولی",
        "تعویض دسته",
        "تعمیر بند",
        "تعویض چرخ",
        "تهمیر چرخ",
        "تعویض پایه",
        "تعمیر پایه",
        "مدبر",
        " تعمیر قفل",
        "تعویض قفل",
        "پیچ"
    )
}