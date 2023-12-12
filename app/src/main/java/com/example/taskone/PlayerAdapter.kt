package com.example.taskone

import android.app.Activity
import android.content.Context

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tableTennis.Player
import com.example.tableTennis.TableTennisClub
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import timber.log.Timber

class PlayerAdapter(private val data:TableTennisClub, private val onClickObject:PlayerAdapter.MyOnClick) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    interface MyOnClick {
        fun onClick(p0: View?, position:Int)
        fun onLongClick(p0: View?, position:Int) : Boolean
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val rvPlayerName : TextView = itemView.findViewById(R.id.rv_player_name)
        val rvPlayerRank : TextView = itemView.findViewById(R.id.rv_player_rank)
        val rvPayerImage : ImageView = itemView.findViewById(R.id.rv_player_image)
        val rvPlayerMembership : TextView = itemView.findViewById(R.id.rv_player_membership)
        val line : CardView = itemView.findViewById(R.id.cv_Line)
        val context: Context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = data.players[position]

        holder.rvPayerImage.setImageResource(R.drawable.default_profile_image)
        Timber.d("MM onBindViewHolder ${data.size()}")
        holder.rvPlayerName.text = "${itemsViewModel.name} ${itemsViewModel.surname}"
        holder.rvPlayerRank.text = itemsViewModel.localRank.toString()
        holder.rvPlayerMembership.text = itemsViewModel.membershipPrice

        val test = MoneyTextWatcher.parseCurrencyValue(itemsViewModel.membershipPrice)
        holder.line.setOnClickListener { p0 ->
            onClickObject.onClick(p0, holder.adapterPosition)
        }

        holder.line.setOnLongClickListener{p0 ->
            onClickObject.onLongClick(p0, holder.adapterPosition)
        }


    }

}