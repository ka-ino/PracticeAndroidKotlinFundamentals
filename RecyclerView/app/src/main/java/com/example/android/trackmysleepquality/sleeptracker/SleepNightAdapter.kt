package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {


    // 表示するリストのデータ
    var data = listOf<SleepNight>()
        // Adapterはdataについて何も知らないので、いつdataが変更されたか知らせる必要がある。
        // このカスタムSetterで新しいvalueを設定し、notifyDataSetChangedで新しいリストを再描画する
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()

        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        } else {
            // 再利用時のためにリセット
            holder.textView.setTextColor(Color.BLACK)
        }
    }

    // 引数のViewGroupはViewHolderを保持するためのもの。
    // ViewTypeは同じRecyclerViewで複数のViewを使用する際の識別用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        // 親ViewGroupからLayoutInflaterを取得
        val inflater = LayoutInflater.from(parent.context)

        // xmlレイアウトと親ViewGroupを渡す。
        // 3番目の引数(attackToRoot)はfalseである必要がある
        // RecyclerViewはこのアイテムをその時点でビュー階層に追加するため
        val view = inflater.inflate(R.layout.text_item_view, parent,false) as TextView

        return TextItemViewHolder(view)
    }
}