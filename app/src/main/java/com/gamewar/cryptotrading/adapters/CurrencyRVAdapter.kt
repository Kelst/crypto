package com.gamewar.cryptotrading.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gamewar.cryptoapi.models.responses.CurrencyResponse
import com.gamewar.cryptotrading.R
import com.gamewar.cryptotrading.databinding.RvCurrencyItemBinding
import com.gamewar.cryptotrading.utils.SVGLoader

class CurrencyRVAdapter(private val listener: ICurrencyRVAdapter): ListAdapter<CurrencyResponse, CurrencyRVAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {

    class CurrencyDiffCallback: DiffUtil.ItemCallback<CurrencyResponse>() {
        override fun areItemsTheSame(oldItem: CurrencyResponse, newItem: CurrencyResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CurrencyResponse, newItem: CurrencyResponse): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

    inner class CurrencyViewHolder(val binding: RvCurrencyItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = RvCurrencyItemBinding.inflate(inflater)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.tvCurrencyName.text = currentItem.name
        holder.binding.tvCurrencySymbol.text = currentItem.symbol
        holder.binding.tvCurrentValue.text = "₹ ${currentItem.price}"

        val length = currentItem.logoUrl!!.length
        if (length != 0) {
            if (currentItem.logoUrl!!.substring(length - 3) == "svg") {
                SVGLoader.fetchSvg(
                    holder.binding.root.context,
                    currentItem.logoUrl!!,
                    holder.binding.ivCurrencyIcon
                )
            } else {
                Glide.with(holder.binding.root.context).load(currentItem.logoUrl)
                    .transform(RoundedCorners(100)).into(holder.binding.ivCurrencyIcon)
            }
        } else {
            Glide.with(holder.binding.root.context).load(R.drawable.ic_android).into(holder.binding.ivCurrencyIcon)
        }

        if (currentItem.oneDay == null && currentItem.sevenDay == null){
            holder.binding.llPercentChange.visibility = View.GONE
        } else {
            if (currentItem.oneDay != null) {
                holder.binding.llPercentChange.visibility = View.VISIBLE

                if (currentItem.oneDay?.priceChangePct?.toDouble()!! >= 0) {
                    holder.binding.tvPercentChange.text =
                        currentItem.oneDay!!.priceChangePct!! + " %"

                    Glide.with(holder.binding.root.context).load(R.drawable.ic_trending_up)
                        .into(holder.binding.ivChangeIcon)
                    holder.binding.ivChangeIcon.setColorFilter(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.green
                        )
                    )
                    holder.binding.tvPercentChange.setTextColor(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.green
                        )
                    )
                } else {
                    holder.binding.tvPercentChange.text =
                        currentItem.oneDay?.priceChangePct?.substring(1) + " %"

                    Glide.with(holder.binding.root.context).load(R.drawable.ic_trending_down)
                        .into(holder.binding.ivChangeIcon)
                    holder.binding.ivChangeIcon.setColorFilter(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.red
                        )
                    )
                    holder.binding.tvPercentChange.setTextColor(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.red
                        )
                    )
                }

            } else if (currentItem.sevenDay != null && currentItem.oneDay == null) {

                if (currentItem.sevenDay?.priceChangePct?.toDouble()!! >= 0) {
                    holder.binding.tvPercentChange.text = currentItem.sevenDay?.priceChangePct + " %"

                    Glide.with(holder.binding.root.context).load(R.drawable.ic_trending_up)
                        .into(holder.binding.ivChangeIcon)
                    holder.binding.ivChangeIcon.setColorFilter(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.green
                        )
                    )
                    holder.binding.tvPercentChange.setTextColor(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.green
                        )
                    )
                } else {
                    holder.binding.tvPercentChange.text = currentItem.sevenDay?.priceChangePct?.substring(1) + " %"

                    Glide.with(holder.binding.root.context).load(R.drawable.ic_trending_down)
                        .into(holder.binding.ivChangeIcon)
                    holder.binding.ivChangeIcon.setColorFilter(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.red
                        )
                    )
                    holder.binding.tvPercentChange.setTextColor(
                        ContextCompat.getColor(
                            holder.binding.root.context,
                            R.color.red
                        )
                    )
                }
            }
        }
        var isChangePositive = true

        if (currentItem.oneDay != null) {
            isChangePositive = currentItem.oneDay?.priceChangePct?.toDouble()!! >= 0
        }else if (currentItem.sevenDay != null) {
            isChangePositive = currentItem.sevenDay?.priceChangePct?.toDouble()!! >= 0
        }

        holder.binding.rlCurrencyRoot.setOnClickListener {
            listener.onCurrencyClicked(currentItem.id!!, isChangePositive)
        }
    }
}

interface ICurrencyRVAdapter {
    fun onCurrencyClicked(currencyId: String, isChangePositive: Boolean)
}