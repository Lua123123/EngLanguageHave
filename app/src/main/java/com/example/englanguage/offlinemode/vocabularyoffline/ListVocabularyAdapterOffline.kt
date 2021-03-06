package com.example.englanguage.offlinemode.vocabularyoffline

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.R
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.offlinemode.OneVocabularyOffActivity
import java.util.*
import kotlin.collections.ArrayList

class ListVocabularyAdapterOffline(private var context: Context?) : RecyclerView.Adapter<ListVocabularyAdapterOffline.ViewHolder>(), Filterable {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private var mTTS: TextToSpeech? = null

    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf<SuccessVocabulary>()) {
        this.mListVocabulary = mListVocabulary
        mListVocabularyOld = mListVocabulary
        notifyDataSetChanged()
    }

    fun reload(mListVocabulary: List<SuccessVocabulary>?) {
        this.mListVocabulary.clear()
        this.mListVocabulary.addAll(mListVocabulary!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val successVocabulary = mListVocabulary!![position] ?: return
        callMTTS()
        if (successVocabulary != null || mListVocabulary != null) {
            holder.tvWord.text = mListVocabulary!![position].word
            holder.tvMean.text = mListVocabulary!![position].mean
            holder.tvExample.text = mListVocabulary!![position].example
            val word = mListVocabulary!![position].word
            val mean = mListVocabulary!![position].mean

            holder.img_detail.setOnClickListener {
                if (holder.tvWord.text == "Determine") {
                    val speech = "/di't??:min/"
                    val translate = context!!.getString(R.string.determine)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Establish") {
                    val speech = "/is't??bli??/"
                    val translate = context!!.getString(R.string.establish)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Engage") {
                    val speech = "/in'geid??/"
                    val translate = context!!.getString(R.string.engage)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Abide by") {
                    val speech = "/??'baid/"
                    val translate = context!!.getString(R.string.abideby)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Assurance") {
                    val speech = "/??'??u??r??ns/"
                    val translate = context!!.getString(R.string.assurance)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Cancellation") {
                    val speech = "/,k??nse'lei??n/"
                    val translate = context!!.getString(R.string.cancellation)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Agreement") {
                    val speech = "/??'gri:m??nt/"
                    val translate = context!!.getString(R.string.agreement)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Satisfaction") {
                    val speech = "/,s??tis'f??k??n/"
                    val translate = context!!.getString(R.string.satisfaction)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Productive") {
                    val speech = "/pr????d??kt??v/"
                    val translate = context!!.getString(R.string.productive)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Persuasion") {
                    val speech = "/p??'swei??n/"
                    val translate: String = context!!.getString(R.string.persuasion)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Market") {
                    val speech = "/'m??:kit/"
                    val translate: String = context!!.getString(R.string.market)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Inspiration") {
                    val speech = "/,insp??'rei??n/"
                    val translate: String = context!!.getString(R.string.inspiration)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Fad") {
                    val speech = "/f??d/"
                    val translate = context!!.getString(R.string.fad)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Currently") {
                    val speech = "/??k??r??ntli/"
                    val translate = context!!.getString(R.string.currently)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Convince") {
                    val speech = "/k??n'vins/"
                    val translate: String = context!!.getString(R.string.convince)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Consume") {
                    val speech = "/k??n'sju:m/"
                    val translate = context!!.getString(R.string.consume)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Competition") {
                    val speech = "/,k??mpi'ti??n/"
                    val translate = context!!.getString(R.string.competition)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Compare") {
                    val speech = "/k??m'pe??/"
                    val translate = context!!.getString(R.string.compare)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Attract") {
                    val speech = "/??'tr??kt/"
                    val translate = context!!.getString(R.string.attract)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Resolve") {
                    val speech = "/ri'z??lv/"
                    val translate = context!!.getString(R.string.resolve)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Specific") {
                    val speech = "/spi'sifik/"
                    val translate = context!!.getString(R.string.specific)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Provision") {
                    val speech = "/provision/"
                    val translate = context!!.getString(R.string.provision)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
            }

            holder.layout_item.setOnClickListener {
                val intent = Intent(context, OneVocabularyOffActivity::class.java)
                intent.putExtra("word", word)
                intent.putExtra("mean", mean)
                context!!.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return if (mListVocabulary != null) {
            mListVocabulary!!.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val tvExample: TextView
        val img_detail: ImageView
        val layout_item: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            tvExample = itemView.findViewById(R.id.example)
            img_detail = itemView.findViewById(R.id.img_detail)
            layout_item = itemView.findViewById(R.id.layout_item)
        }
    }

    fun release() {
        context = null
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val strSearch = charSequence.toString()
                mListVocabulary = if (strSearch.isEmpty()) {
                    mListVocabularyOld
                } else {
                    val list: MutableList<SuccessVocabulary> = ArrayList()
                    for (successVocabulary in mListVocabularyOld!!) {
                        if (successVocabulary.word.toLowerCase()
                                .contains(strSearch.toLowerCase())
                        ) {
                            list.add(successVocabulary)
                        }
                    }
                    list
                }
                val filterResults = FilterResults()
                filterResults.values = mListVocabulary
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mListVocabulary = (filterResults.values as List<SuccessVocabulary>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    fun openDialogShowVocabulary(gravity: Int, position: Int, speech: String, translate: String) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_view_xemchitiet)
        val window = dialog.window
        if (window == null) {
            return
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val wordDialog = dialog.findViewById<TextView>(R.id.wordDialog)
        val speechDialog = dialog.findViewById<TextView>(R.id.speechDialog)
        val meanDialog = dialog.findViewById<TextView>(R.id.meanDialog)
        val exampleDialog = dialog.findViewById<TextView>(R.id.exampleDialog)
        val translateDialog = dialog.findViewById<TextView>(R.id.translateDialog)
        val imgVolumeDialog = dialog.findViewById<ImageView>(R.id.imgVolumeDialog)
        val btnConFirmDialog: Button = dialog.findViewById(R.id.btnConFirmDialog)
        val word: String = mListVocabulary[position].word
        val mean: String = mListVocabulary[position].mean
        val example: String = mListVocabulary[position].example
        wordDialog.text = word
        speechDialog.text = speech
        meanDialog.text = mean
        exampleDialog.text = example
        translateDialog.text = translate

        btnConFirmDialog.setOnClickListener {
            dialog.dismiss()
        }

        imgVolumeDialog.setOnClickListener {
            speak(word)
        }

        dialog.show()
    }

    fun callMTTS() {
        mTTS = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported")
                    }
                    run { Log.e("TTS", "Initialization failed") }
                }
            }
        })
    }

    fun speak(word: String) {
        var text: String? = word
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}