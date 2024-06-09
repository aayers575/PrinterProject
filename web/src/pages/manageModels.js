import Authenticator from '../api/authenticator';
import ModelsClient from '../api/modelsClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const MODEL_LIST_KEY = 'model-list-key';
const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


class ManageModels extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['editModel', 'mount', 'editButton', 'addModel', 'addButton', 'deleteButton',
         'deleteModel', 'search', 'displaySearchResults', 'getHTMLForSearchResults'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }


    mount() {
        document.getElementById('edit-btn').addEventListener('click', this.editButton);
        document.getElementById('add-btn').addEventListener('click', this.addButton);
        document.getElementById('delete-btn').addEventListener('click', this.deleteButton);
        document.getElementById('search-btn').addEventListener('click', this.search);
        this.modelsClient = new ModelsClient();
        this.header.addHeaderToPage();
    }

    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchCriteria = document.getElementById('search_keyword').value;


        if (searchCriteria) {
            const results = await this.modelsClient.getMultipleModels(searchCriteria);
            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: searchCriteria,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (searchCriteria === '') {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        } else {
            searchResultsContainer.classList.remove('hidden');
            //searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
            searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
        }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
        if (searchResults.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>Id</th><th>Keyword</th><th>Link</th><th>Length</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                    <a href="models.html?id=${res.id}">${res.modelId}</a>
                </td>
                <td>${res.keyword}</td>
                <td>${res.link}</td>
                <td>${res.linkRemaining}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

    async editButton() {
        if(document.getElementById('model-id').value == "" || document.getElementById('model-keyword').value == "" ||
        document.getElementById('model-link').value == 0 || document.getElementById('model-length').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.editModel();
           alert("Model updated!");
        }
    }

    async editModel() {
        const modelId = document.getElementById('model-id').value;
        const link = document.getElementById('model-link').value;
        const length = document.getElementById('model-length').value;
        const isActive = document.getElementById('add-isActive').value;
        const keyword = document.getElementById('model-keyword').value;

            await this.modelsClient.editModel(modelId, link, length, isActive, keyword)

    }

    async addButton() {
        if(document.getElementById('add-model-keyword').value == "" ||
        document.getElementById('add-model-link').value == 0 || document.getElementById('add-model-length').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.addModel();
           alert("Model added!");
        }
    }

    async addModel() {
        const link = document.getElementById('add-model-link').value;
        const linkRemaining = document.getElementById('add-model-length').value;
        const isActive = document.getElementById('add-isActive').value;
        const keyword = document.getElementById('add-model-keyword').value;

            await this.modelsClient.addModel(link, linkRemaining, isActive, keyword)

    }

    async deleteButton() {
        if(document.getElementById('delete-id').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.deleteModel();
           alert("Model deleted!");
        }
    }

    async deleteModel() {
        const keyword = new URLSearchParams(window.location.search).get('keyword');
        const modelId = document.getElementById('delete-id').value;
        await this.modelsClient.deleteModel(keyword, modelId)
    }



}
const main = async () => {
    const manageModels = new ManageModels();
    manageModels.mount();
};

window.addEventListener('DOMContentLoaded', main);