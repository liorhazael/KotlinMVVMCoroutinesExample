package com.example.kotlinmvvmcoroutinesexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.kotlinmvvmcoroutinesexample.R
import com.example.kotlinmvvmcoroutinesexample.model.network.Character

/**
 * @author Lior Hazael
 */
class MainAdapter(var charactersList : MutableList<Character>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
       return charactersList.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         fun bindData(character: Character){
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = character.name
            image.load(character.image){
                transformations(CircleCropTransformation())
            }
        }

    }
}