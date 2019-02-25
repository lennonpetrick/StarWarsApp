package com.test.starwarsapp.presentation.character.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import com.test.starwarsapp.R
import com.test.starwarsapp.domain.models.Character
import com.test.starwarsapp.presentation.base.BaseActivity
import com.test.starwarsapp.presentation.base.adapter.BaseAdapter
import com.test.starwarsapp.presentation.character.detail.CharacterDetailActivity
import com.test.starwarsapp.presentation.character.search.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.activity_character_search.*

class CharacterSearchActivity : BaseActivity<CharacterSearchContract.Presenter>(),
        CharacterSearchContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_search)
        setUpToolbar(true)
        setUpRecyclerView()

        presenter.view(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        setUpSearch(menu)
        return true
    }

    override fun onNewIntent(intent: Intent?) {
        setIntent(intent)
        handleIntent(intent)
    }

    private fun setUpRecyclerView() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvCharacters.addItemDecoration(divider)
        rvCharacters.layoutManager = LinearLayoutManager(this)
        rvCharacters.setHasFixedSize(false)
    }

    private fun setUpSearch(menu: Menu?) {
        menu?.findItem(R.id.action_search)?.apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            (actionView as SearchView)
                    .setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
    }

    override fun setCharacters(characters: List<Character>) {
        val adapter: CharacterAdapter? = rvCharacters.adapter as CharacterAdapter?
        if (adapter == null) {
            rvCharacters.adapter = CharacterAdapter(characters.toMutableList())
                    .apply {
                        this.onItemClickListener = object:
                                BaseAdapter.OnItemClickListener<Character> {
                            override fun onItemClick(item: Character) {
                                startCharacterDetailScreen(item)
                            }
                        }
                    }
        } else {
            adapter.setItems(characters)
        }
    }

    private fun startCharacterDetailScreen(character: Character) {
        startActivity(
                Intent(this, CharacterDetailActivity::class.java).also {
                    it.putExtra(Character::class.java.name, character.url)
                }
        )
    }

    private fun handleIntent(intent: Intent?) {
        intent?.apply {
            if (Intent.ACTION_SEARCH == action) {
                val query = intent.getStringExtra(SearchManager.QUERY).trim()
                presenter.setSearch(query)
                presenter.load()
            }
        }
    }
}
