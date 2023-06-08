package hr.codetome.starwars.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import hr.codetome.starwars.adapter.FilmsAdapter
import hr.codetome.starwars.databinding.FragmentCharactersDetailsBinding
import hr.codetome.starwars.utils.Resource
import hr.codetome.starwars.viewmodel.CharacterDetailsViewModel
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCharactersDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.details.observe(viewLifecycleOwner) { result ->
            binding.textViewFullNameValue.text = result.name
            binding.textViewSkinColorValue.text = result.skinColor
            binding.textViewHairColorValue.text = result.hairColor
            binding.textViewHeightValue.text = result.height
            binding.textViewMassValue.text = result.mass
            binding.textViewEyeColorValue.text = result.eyeColor
            binding.textViewGenderValue.text = result.gender
            binding.textViewBirthYearValue.text = result.birthYear
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filmResponseDetails.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        binding.filmProgressBar.isVisible = false
                        filmsAdapter.submitList(event.data)
                        binding.recyclerViewFilms.adapter = filmsAdapter
                    }
                    is Resource.Failure -> {
                        binding.filmProgressBar.isVisible = false
                        binding.textViewFilmsError.isVisible = true
                        binding.textViewFilmsError.text = event.message
                    }
                    is Resource.Loading -> {
                        binding.filmProgressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeWorldResponse.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.data!!.name
                    }
                    is Resource.Failure -> {
                        binding.progressBarHomeWord.isVisible = false
                        binding.textViewHomeWorldValue.text = event.message
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {
                        binding.progressBarHomeWord.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

        return view
    }
}