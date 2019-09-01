package com.example.android.trackmysleepquality.sleeptracker

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding
import com.example.android.trackmysleepquality.sleeptracker.SleepNightAdapter.ViewHolder.Companion.from


//class SleepNightAdapter: RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

//    // 表示するリストのデータ
//    var data = listOf<SleepNight>()
//        // Adapterはdataについて何も知らないので、いつdataが変更されたか知らせる必要がある。
//        // このカスタムSetterで新しいvalueを設定し、notifyDataSetChangedで新しいリストを再描画する
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

//    override fun getItemCount() = data.size

    // onCreateViewHolder > ViewHolder > onBindViewHolderの順に呼ばれる

    // 引数のViewGroupはViewHolderを保持するためのもの。
    // ViewTypeは同じRecyclerViewで複数のViewを使用する際の識別用
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("SleepNightAdapter", "Called onCreateViewHolder()")

        return from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView
    .ViewHolder(binding.root){


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                Log.d("ViewHolder", "Called ViewHolder.from()")
                // 親ViewGroupからLayoutInflaterを取得
                val inflater = LayoutInflater.from(parent.context)

                // xmlレイアウトと親ViewGroupを渡す。
                // 3番目の引数(attackToRoot)はfalseである必要がある
                // RecyclerViewはこのアイテムをその時点でビュー階層に追加するため
//                val view = inflater.inflate(R.layout.list_item_sleep_night, parent, false)
                // ↑をDataBindingに変更
                val binding = ListItemSleepNightBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }

//        fun bind(item: SleepNight) {
//            Log.d("ViewHolder", "ViewHolder.bind called")
//
//            val res = itemView.context.resources
//            binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//            binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
//            binding.qualityImage.setImageResource(when (item.sleepQuality) {
//                0 -> R.drawable.ic_sleep_0
//                1 -> R.drawable.ic_sleep_1
//                2 -> R.drawable.ic_sleep_2
//                3 -> R.drawable.ic_sleep_3
//                4 -> R.drawable.ic_sleep_4
//                5 -> R.drawable.ic_sleep_5
//                else -> R.drawable.ic_sleep_active
//            })
//        }
        // DataBindingとAdapterで↑を変更
        fun bind(item: SleepNight) {
    Log.d("SleepNightAdapter", "Called ViewHolder.bind()")
            binding.sleep = item
            // 保留中のBindingをすぐに実行するように要求する最適化
            binding.executePendingBindings()

}


}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("SleepNightAdapter", "Called onBindViewHolder()")
//        val item = data[position]
        val item = getItem(position)

        holder.bind(item)
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {

    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        Log.d("SleepNightAdapter", "Called areItemsTheSame: ${oldItem.nightId == newItem.nightId}")

        // nightIdが一致しているかチェック
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        Log.d("SleepNightAdapter", "Called areContentsTheSame: ${oldItem == newItem}")
        // Dataのフィールドが完全に一致しているかチェック
        return oldItem == newItem
    }
}