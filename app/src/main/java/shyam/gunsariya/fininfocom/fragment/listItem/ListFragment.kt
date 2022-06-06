package shyam.gunsariya.fininfocom.fragment.listItem

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import shyam.gunsariya.fininfocom.R
import shyam.gunsariya.fininfocom.adapter.ListAdapter
import shyam.gunsariya.fininfocom.databinding.ListFragmentLayoutBinding
import shyam.gunsariya.fininfocom.model.ListResponse

class ListFragment:Fragment() {

    private lateinit var binding: ListFragmentLayoutBinding
    private val adapter : ListAdapter by inject()
    private val viewModel by viewModel<ListItemViewModel>()
    var list = ArrayList<ListResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        viewModel.listItem.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty().not()){
                list = it
                adapter.updateList(it)
                binding.itemRecycler.adapter = adapter
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.filterByName->{
                list.sortWith(
                    compareBy(String.CASE_INSENSITIVE_ORDER) { it.name }
                )
                adapter.updateList(list)
                adapter.notifyDataSetChanged()
            }
            R.id.filterByCity->{
                list.sortWith(
                    compareBy(String.CASE_INSENSITIVE_ORDER) { it.city }
                )
                adapter.updateList(list)
                adapter.notifyDataSetChanged()
            }
            R.id.filterByAge->{
                list.sortWith(
                    compareBy(String.CASE_INSENSITIVE_ORDER) { it.age.toString() }
                )
                adapter.updateList(list)
                adapter.notifyDataSetChanged()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItemList()
    }
}