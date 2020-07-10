package com.isminr.capi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isminr.capi.model.DataItem
import com.isminr.capi.present.CrudView
import com.isminr.capi.present.Presenter
import kotlinx.android.synthetic.main.activity_form.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class FormActivity : AppCompatActivity(), CrudView {


    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        btnAction.onClick {
            presenter.addData(
                etName.text.toString(),
                etPhone.text.toString(),
                etAlamat.text.toString())
        }
    }



    override fun successAdd(msg: String) {
        startActivity<MainActivity>()
        finish()
    }

    override fun errorAdd(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}

    override fun onFailedGet(msg: String) {}

}
