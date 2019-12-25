package br.com.phs.roomexample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.phs.roomexample.R
import br.com.phs.roomexample.data.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
    private val items: List<User>, private val context: Context
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameItemLabel.text = items[position].firstName
        holder.lastNameLabel.text = items[position].lastName
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameItemLabel = view.nameItemLabel!!
        val lastNameLabel = view.lastNameItemLabel!!
    }

}