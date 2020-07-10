package com.isminr.capi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isminr.capi.adapter.DataAdapter
import com.isminr.capi.model.DataItem
import com.isminr.capi.present.CrudView
import com.isminr.capi.present.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "C KOTLIN CI API"

        presenter = Presenter(this)
        presenter.getData()

        btnTambah.setOnClickListener {
            startActivity<FormActivity>()
            finish()
        }
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory.adapter = DataAdapter(data, object :
            DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                startActivity<FormActivity>("dataItem" to item)
            }

            override fun delete(item: DataItem?) {
//                presenter.hapusData(item?.staffId)
//                startActivity<MainActivity>()
//                finish()
            }
        })
    }

    override fun onFailedGet(msg: String) {
    }

    override fun successAdd(msg: String) {
    }

    override fun errorAdd(msg: String) {
    }

}
