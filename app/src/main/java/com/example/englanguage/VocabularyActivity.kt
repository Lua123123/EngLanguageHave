package com.example.englanguage

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.englanguage.adapter.ListVocabularyAdapter
import com.example.englanguage.helper.MyButtonClickListener
import com.example.englanguage.helper.MySwipeHelperJV
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.viewmodel.LoginFragmentViewModel
import com.example.englanguage.viewmodel.VocabularyViewModel
import de.hdodenhof.circleimageview.CircleImageView

class VocabularyActivity : AppCompatActivity() {

    private val context: Context = this@VocabularyActivity
    private var recyclerView: RecyclerView? = null
    private var adapter: ListVocabularyAdapter? = null
    private var mListVocabulary: List<SuccessVocabulary> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var vocabularyViewModel: VocabularyViewModel? = null
    private val search: String = ""
    private var searchView: SearchView? = null
    private var addVocabulary: CircleImageView? = null
    private var imgBack: ImageView? = null
    private var authorization: String? = null
    private var loginViewModel: LoginFragmentViewModel? = null
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefreshLayout)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
        val intent = intent
        val bundle = intent.extras
        authorization = bundle!!.getString("Authorization")
        addVocabulary = findViewById(R.id.addVocabulary)
        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setLayoutManager(layoutManager)
        adapter = ListVocabularyAdapter(context)
        recyclerView?.setAdapter(adapter)

        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        recyclerView?.addItemDecoration(itemDecoration)

        loginViewModel?.mAuthor?.observe(this) {
            authorization = it
        }

//        authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiMTllOTAzNDY2OWIwZmQzZmMxMGE0ZjlkNTNjNGJmNDQxNjc5MDE2YTUyMzdkZDEzMTgwZjhkNmFkMDkwMGE0NTY1Mjk4NDBmYjFjYjg0ZjAiLCJpYXQiOjE2NTI3NzAxNTEuMTgyMzI0LCJuYmYiOjE2NTI3NzAxNTEuMTgyMzM2LCJleHAiOjE2ODQzMDYxNTEuMTY4NTA5LCJzdWIiOiI5Iiwic2NvcGVzIjpbXX0.G0-AbCxIIfWTmHKLlVM88UKBaReKi3oIAkY2mlY2FTgjb55DzJUOW5rKTMkh3nUg2VuVhQvSEKVlRc6ARoKrr5e58WrTXEqAoRM8pTVYk4XYXG-5nVFkRGhcMEplXr9HkhxwUBaDwCUT29nnPeuFvwVmhU2etVN2C5X07RhznWnjSlp-Y1ohbz4DwCGt8yvlSWZDYgr2we2eiPbTvtsRi4aakh0Xi1dOjxaD3ULRNlqhUmoyAgUvC2IpLbg1D9NyMjJ1DA7H_Zu9NuAtf3tyD7C2elUUJXBzxfzRC5RtlyWtMDu7Cb6vvEVUX4FKDlIF_MqjZA2kejPEQ6MNVF7hmo0eqvMwfQkIv0jXDFWoxwmJQ9zNQw9CHvfYgBqSAqhp-GlhIvDCdMmpUPfe9BRizAo3M04Ox0Y0RluaWjeB-x1JzRm_ch6o9OGK1QK_YllFhchuY_EbN3-wypoPZ7YKvsU4fitgDE3W09TP1Ic38d45s-O4JpxI5qloHqig5X6UDaRV5IwHehq6jfDEMJtszqCe9fO7ASgg0YOV9sDSHgO9RiwRc3zfAHuKeMxNSVf1FeiGStJ5MkUEEj4OmGkTjAUoZFeVCXMBY1WxA3eywm3N6ITDS-T3k7Qr9uVROOlAfRUdn9MVA-5GxNetQLwvgPuiv-8lNGFW9awWAYkrvxk"

        vocabularyViewModel = VocabularyViewModel()

        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener(View.OnClickListener {
            val intentMainActivity = Intent(this@VocabularyActivity, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("Authorization", authorization)
            intentMainActivity.putExtras(bundle)
            startActivity(intentMainActivity)
        })

        vocabularyViewModel!!.mClickGetVocabulary(recyclerView!!, adapter!!, context, search)

        addVocabulary?.setOnClickListener(View.OnClickListener {
            vocabularyViewModel!!.mOpenDialogInsertVocabulary(Gravity.CENTER, authorization, context)
        })

        //Hàm vuốt xoá
        val swipeHelper: MySwipeHelperJV = object : MySwipeHelperJV(this, recyclerView, 200) {
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(
                    MyButton(
                        Gravity.CENTER,
                        this@VocabularyActivity,
                        "Delete",
                        50,
                        0,
                        Color.parseColor("#2e3648"), object : MyButtonClickListener {
                            override fun onClick(pos: Int) {
                                var position: Int = viewHolder.adapterPosition
                                vocabularyViewModel?.mOpenDialogDeleteVocabulary(
                                    Gravity.CENTER,
                                    authorization,
                                    position,
                                    swipeRefreshLayout,
                                    context
                                );
                            }
                        }
                    )
                )
            }
        }

        //LOAD LAI
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            vocabularyViewModel!!.mClickGetVocabulary(recyclerView!!, adapter!!, context, search)
            adapter?.reload(mListVocabulary)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                adapter!!.filter.filter(s)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter!!.filter.filter(s)
                return false
            }
        })
        return true
    }

    //HÀM KHI BẤM VÀO SEARCH VÀ TẮT BÀN PHÍM ĐI
    //THÌ NHẤN NEXT SẼ THOÁT NÚT SEARCH, NEXT LẦN NỮA MỚI THOÁT RA GIAO DIỆN KHÁC
    //THAY VÌ ĐANG TRONG CHẾ ĐỘ SEARCH NHẤN NEXT NÓ VỀ GIAO DIỆN KHÁC THÌ KHÔNG ĐÚNG
    override fun onBackPressed() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            return
        }
        val intent5 = Intent(this@VocabularyActivity, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putString("Authorization", authorization)
        intent5.putExtras(bundle)
        startActivity(intent5)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adapter != null) {
            adapter!!.release()
        }
    }
}