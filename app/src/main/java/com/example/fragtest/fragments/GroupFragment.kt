package com.example.fragtest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragtest.adapters.ListGroupAdapter
import com.example.fragtest.data.MusicGroupService
import com.example.fragtest.data.MusicGroups
import com.example.fragtest.databinding.ItemFragmentListGroupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupFragment : Fragment()
{
    /**/
    // 1. Declarar la variable de binding
    // Usamos 'var?' para que sea nullable, ya que debe ser nulo después de onDestroyView
    private var _binding: ItemFragmentListGroupBinding? = null
    // 2. Propiedad para acceder al binding de forma segura (sin la necesidad de '!!')
    private val binding get() = _binding!!
    /**/
    lateinit var adapter: ListGroupAdapter
    var originalVgList: List<MusicGroups> = emptyList()
    var filteredVgList: List<MusicGroups> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //
        adapter = ListGroupAdapter(filteredVgList) { position ->
            val listGroup = filteredVgList[position]
            //val intent = Intent(this, DetailActivity::class.java)
            //intent.putExtra(DetailActivity.PUT_EXTRA_GAME_ID, videoGame.id)
            //startActivity(intent)
        }
        // binding.idRvVideoGames.adapter = adapter
        // binding.idRvVideoGames.layoutManager = LinearLayoutManager(this)

        getGroupList()
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_group, container, false)
        // 3. Inflar el layout usando la clase de binding generada
        _binding = ItemFragmentListGroupBinding.inflate(inflater, container, false)

        // Retornar la vista raíz
        return binding.root
    }

    /**/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 4. Acceder a las vistas directamente a través del objeto 'binding'
        // El ID de tu RecyclerView en el XML era 'recyclerViewLicores'
        //binding.recyclerViewLicores.layoutManager = LinearLayoutManager(context)

        // Ahora llama a tu función de carga de datos
        getGroupList()
    }

    // 5. Función para manejar la limpieza del binding
    override fun onDestroyView() {
        super.onDestroyView()
        // Es crucial establecer el binding a null para liberar la referencia de la vista
        // y evitar fugas de memoria cuando el Fragment es destruido.
        _binding = null
    }


    /*We create the getGameList function*/
    fun getGroupList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = MusicGroupService.getInstance()
                originalVgList = service.getAllGroups()
                filteredVgList = originalVgList
                CoroutineScope(Dispatchers.Main).launch {
                    adapter.updateItems(filteredVgList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GroupFragment().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }*/
}