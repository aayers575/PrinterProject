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
    [FILAMENT_LIST_KEY]: []
};


class ManageFilaments extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['editFilament', 'mount', 'editButton', 'addFilament', 'addButton', 'deleteButton',
         'deleteFilament', 'search', 'populateSearchTable', 'displaySearchResults', 'getHTMLForSearchResults', 'populateTable', 'startupActivities'], this);
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
        this.startupActivities();
    }

    async startupActivities() {
            this.dataStore.set([FILAMENT_LIST_KEY], await this.filamentsClient.getAllFilaments());
            await this.populateTable();
            var preloads = document.getElementsByClassName('preload')
            for (var i= 0; i < preloads.length; i++) {
                preloads[i].hidden=false
            }
            document.getElementById('loading').hidden=true;
    }

    async populateTable() {
        var table = document.getElementById("filament-table");
        var oldTableBody = table.getElementsByTagName('tbody')[0];
        var newTableBody = document.createElement('tbody');
        var filamentList = this.dataStore.get(FILAMENT_LIST_KEY);


        for(const filament of filamentList) {
                var row = newTableBody.insertRow(0);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                cell1.innerHTML = filament.color;
                cell2.innerHTML = filament.material;
                cell3.innerHTML = filament.materialRemaining;
                cell4.innerHTML = filament.isActive;
                var createClickHandler = function(row) {
                    return function() {
                        for (var i = 0; i < table.rows.length; i++){
                            table.rows[i].removeAttribute('class');
                        }
                        row.setAttribute('class','selectedRow')
                         document.getElementById('filament-id').value = filament.filamentId
                         document.getElementById('filament-color').value = filament.color
                         document.getElementById('filament-material').value = filament.material
                         document.getElementById('filament-length').value = filament.materialRemaining;
                    };
                };
                row.onclick = createClickHandler(row);
        }
        oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
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
        } else {
            searchResultsContainer.classList.remove('hidden');
            this.populateSearchTable();
        }
    }

    async populateSearchTable() {
        var table = document.getElementById("search-table");
        var oldTableBody = table.getElementsByTagName('tbody')[0];
        var newTableBody = document.createElement('tbody');
        var filamentList = this.dataStore.get(SEARCH_RESULTS_KEY);


        for(const filament of filamentList) {
                var row = newTableBody.insertRow(0);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                cell1.innerHTML = filament.color;
                cell2.innerHTML = filament.material;
                cell3.innerHTML = filament.materialRemaining;
                cell4.innerHTML = filament.isActive;
                var createClickHandler = function(row) {
                    return function() {
                        for (var i = 0; i < table.rows.length; i++){
                            table.rows[i].removeAttribute('class');
                        }
                        row.setAttribute('class','selectedRow')
                         document.getElementById('filament-id').value = filament.filamentId
                         document.getElementById('filament-color').value = filament.color
                         document.getElementById('filament-material').value = filament.material
                         document.getElementById('filament-length').value = filament.materialRemaining;
                    };
                };
                row.onclick = createClickHandler(row);
        }
        oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
        document.getElementById('loading1').hidden=true;
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
        if(document.getElementById('filament-id').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.deleteFilament();
           alert("Filament deleted!");
        }
    }

    async deleteFilament() {
        const filamentId = document.getElementById('filament-id').value;
        await this.filamentsClient.deleteFilament(filamentId)
    }



}
const main = async () => {
    const manageFilaments = new ManageFilaments();
    manageFilaments.mount();
};

window.addEventListener('DOMContentLoaded', main);