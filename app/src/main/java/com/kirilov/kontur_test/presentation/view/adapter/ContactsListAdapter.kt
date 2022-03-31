package com.kirilov.kontur_test.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kirilov.kontur_test.databinding.RecyclerviewItemContactBinding
import com.kirilov.kontur_test.presentation.model.ContactModel
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class ContactsListAdapter
@Inject constructor() : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    internal var collection: List<ContactModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    lateinit var clickListener: ContactModel.ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(collection[position], clickListener)

    override fun getItemCount(): Int = collection.size

    class ViewHolder(private val binding: RecyclerviewItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contactModel: ContactModel, clickListener: ContactModel.ItemClickListener) {
            binding.apply {
                model = contactModel
                root.setOnClickListener { clickListener.onClick(contactModel) }
                name.text = contactModel.name
//                executePendingBindings()
            }
        }
    }
}