package com.gornostai.rickandmorty.presentation.screens.characters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gornostai.rickandmorty.R
import com.gornostai.rickandmorty.databinding.DialogCharactersFilterBinding
import com.gornostai.rickandmorty.databinding.FragmentCharactersBinding
import com.gornostai.rickandmorty.domain.entities.CharacterFilterEntity
import com.gornostai.rickandmorty.presentation.contracts.HasCustomTitle
import com.gornostai.rickandmorty.presentation.contracts.HasFilterButton
import com.gornostai.rickandmorty.presentation.contracts.HasSearchButton
import com.gornostai.rickandmorty.presentation.contracts.navigator
import com.gornostai.rickandmorty.presentation.screens.characterDetails.CharacterDetailsFragment
import com.gornostai.rickandmorty.presentation.screens.characters.adapters.CharactersAdapter
import com.gornostai.rickandmorty.presentation.screens.characters.adapters.SpacesItemDecoration
import com.gornostai.rickandmorty.utils.App
import com.gornostai.rickandmorty.utils.CharacterGender
import com.gornostai.rickandmorty.utils.CharacterStatus
import com.gornostai.rickandmorty.utils.ViewModelFactory
import javax.inject.Inject

class CharactersFragment : Fragment(), HasCustomTitle, HasFilterButton, HasSearchButton {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var viewModel: CharactersViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy { CharactersAdapter() }
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersViewModel::class.java]
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupData()
        setupLoading()
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    override fun getTitleRes(): Int = R.string.characters_title

    override fun onFilterPressed() {
        showFilterDialog(viewModel.filter)
    }

    override fun onSearchPressed() {

    }

    private fun setupRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacters.addItemDecoration(SpacesItemDecoration(16f, requireContext()))
        binding.rvCharacters.adapter = adapter
        adapter.onCharacterItemClickListener = {
            navigator().setFragment(
                newFragment = CharacterDetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }
    }

    private fun setupData() {
        viewModel.charactersList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvEmptyMessage.visibility = View.VISIBLE
            } else {
                binding.tvEmptyMessage.visibility = View.GONE
            }
            adapter.submitList(it)
        }
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }
    }

    private fun showFilterDialog(filter: CharacterFilterEntity) {
        val binding = DialogCharactersFilterBinding.inflate(layoutInflater)
        binding.edCharacterName.setText(filter.name)
        binding.edCharacterSpecies.setText(filter.species)
        binding.edCharacterType.setText(filter.type)
        when (filter.gender) {
            CharacterGender.FEMALE.value -> binding.genderFemale.isChecked = true
            CharacterGender.MALE.value -> binding.genderMale.isChecked = true
            CharacterGender.GENDERLESS.value -> binding.genderGenderless.isChecked = true
            CharacterGender.UNKNOWN.value -> binding.genderUnknown.isChecked = true
            else -> {}
        }
        when (filter.status) {
            CharacterStatus.ALIVE.value -> binding.statusAlive.isChecked = true
            CharacterStatus.DEAD.value -> binding.statusDead.isChecked = true
            CharacterStatus.UNKNOWN.value -> binding.statusUnknown.isChecked = true
            else -> {}
        }
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.getFilteredData(
                        CharacterFilterEntity(
                            name = binding.edCharacterName.text.toString(),
                            species = binding.edCharacterSpecies.text.toString(),
                            type = binding.edCharacterType.text.toString(),
                            status = when (binding.radioGroupStatus.checkedRadioButtonId) {
                                R.id.status_alive -> CharacterStatus.ALIVE.value
                                R.id.status_dead -> CharacterStatus.DEAD.value
                                R.id.status_unknown -> CharacterStatus.UNKNOWN.value
                                else -> ""
                            },
                            gender = when (binding.radioGroupGender.checkedRadioButtonId) {
                                R.id.gender_female -> CharacterGender.FEMALE.value
                                R.id.gender_male -> CharacterGender.MALE.value
                                R.id.gender_genderless -> CharacterGender.GENDERLESS.value
                                R.id.gender_unknown -> CharacterGender.UNKNOWN.value
                                else -> ""
                            }
                        )
                    )
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    viewModel.fetchData()
                }
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setView(binding.root)
            .setTitle(getString(R.string.dialog_characters_filter))
            .setPositiveButton(getString(R.string.apply), listener)
            .setNegativeButton(getString(R.string.cancel), listener)
            .setNeutralButton(getString(R.string.clear), listener)
            .create()
        dialog.show()
    }

    companion object {

        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

}