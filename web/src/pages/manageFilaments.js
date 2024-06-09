import Authenticator from '../api/authenticator';
import FilamentsClient from '../api/filamentsClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const FILAMENT_LIST_KEY = 'filament-list-key';
const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


class ManageFilaments extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['editFilament', 'mount', 'editButton', 'addFilament', 'addButton', 'deleteButton',
         'deleteFilament', 'search', 'displaySearchResults', 'getHTMLForSearchResults'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }


    mount() {
        document.getElementById('edit-btn').addEventListener('click', this.editButton);
        document.getElementById('add-btn').addEventListener('click', this.addButton);
        document.getElementById('delete-btn').addEventListener('click', this.deleteButton);
        document.getElementById('search-btn').addEventListener('click', this.search);
        this.filamentsClient = new FilamentsClient();
        this.header.addHeaderToPage();
    }

    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchCriteria = document.getElementById('search_color').value;


        if (searchCriteria) {
            const results = await this.filamentsClient.getMultipleFilaments(searchCriteria);
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

        let html = '<table><tr><th>Id</th><th>Color</th><th>Material</th><th>Length</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                    <a href="filaments.html?id=${res.id}">${res.filamentId}</a>
                </td>
                <td>${res.color}</td>
                <td>${res.material}</td>
                <td>${res.materialRemaining}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

    async editButton() {
        if(document.getElementById('filament-id').value == "" || document.getElementById('filament-color').value == "" ||
        document.getElementById('filament-material').value == 0 || document.getElementById('filament-length').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.editFilament();
           alert("Filament updated!");
        }
    }

    async editFilament() {
        const filamentId = document.getElementById('filament-id').value;
        const material = document.getElementById('filament-material').value;
        const length = document.getElementById('filament-length').value;
        const isActive = document.getElementById('add-isActive').value;
        const color = document.getElementById('filament-color').value;

            await this.filamentsClient.editFilament(filamentId, material, length, isActive, color)

    }

    async addButton() {
        if(document.getElementById('add-filament-color').value == "" ||
        document.getElementById('add-filament-material').value == 0 || document.getElementById('add-filament-length').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.addFilament();
           alert("Filament added!");
        }
    }

    async addFilament() {
        const material = document.getElementById('add-filament-material').value;
        const materialRemaining = document.getElementById('add-filament-length').value;
        const isActive = document.getElementById('add-isActive').value;
        const color = document.getElementById('add-filament-color').value;

            await this.filamentsClient.addFilament(material, materialRemaining, isActive, color)

    }

    async deleteButton() {
        if(document.getElementById('delete-id').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.deleteFilament();
           alert("Filament deleted!");
        }
    }

    async deleteFilament() {
        const color = new URLSearchParams(window.location.search).get('color');
        const filamentId = document.getElementById('delete-id').value;
        await this.filamentsClient.deleteFilament(color, filamentId)
    }



}
const main = async () => {
    const manageFilaments = new ManageFilaments();
    manageFilaments.mount();
};

window.addEventListener('DOMContentLoaded', main);