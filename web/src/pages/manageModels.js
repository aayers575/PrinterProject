import Authenticator from '../api/authenticator';
import ModelsClient from '../api/modelsClient';
import FilamentsClient from '../api/filamentsClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const MODEL_LIST_KEY = 'model-list-key';
const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
    [MODEL_LIST_KEY]: []
};


class ManageModels extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['editModel', 'mount', 'editButton', 'addModel', 'addButton', 'deleteButton',
         'deleteModel', 'search', 'displaySearchResults','populateSearchTable', 'startupActivities', 'printButton', 'printModel'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }


    mount() {
        document.getElementById('edit-btn').addEventListener('click', this.editButton);
        document.getElementById('add-btn').addEventListener('click', this.addButton);
        document.getElementById('delete-btn').addEventListener('click', this.deleteButton);
        document.getElementById('print-btn').addEventListener('click', this.printButton);
        document.getElementById('search-btn').addEventListener('click', this.search);
        this.modelsClient = new ModelsClient();
        this.filamentsClient = new FilamentsClient();
        this.header.addHeaderToPage();
        this.startupActivities();
    }

    async startupActivities() {
            var preloads = document.getElementsByClassName('preload')
            for (var i= 0; i < preloads.length; i++) {
                preloads[i].hidden=false
            }
            if (localStorage.getItem("id") != null) {
                document.getElementById('filament').hidden = false;
                document.getElementById('filament-container').hidden = false
            }
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
        } else {
            searchResultsContainer.classList.remove('hidden');
            this.populateSearchTable();
        }
    }

    async populateSearchTable() {
        var table = document.getElementById("search-table");
        var oldTableBody = table.getElementsByTagName('tbody')[0];
        var newTableBody = document.createElement('tbody');
        var modelList = this.dataStore.get(SEARCH_RESULTS_KEY);


        for(const model of modelList) {
                var row = newTableBody.insertRow(0);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                cell1.innerHTML = model.keyword;
                cell2.innerHTML = '<a href="' + model.preview + "\">" + model.preview + '</a>';
                cell3.innerHTML = model.materialUsed;
                cell4.innerHTML = model.isActive;
                var createClickHandler = function(row) {
                    return function() {
                        for (var i = 0; i < table.rows.length; i++){
                            table.rows[i].removeAttribute('class');
                        }
                        row.setAttribute('class','selectedRow')
                         document.getElementById('model-id').value = model.modelId
                         document.getElementById('model-keyword').value = model.keyword
                         document.getElementById('model-link').value = model.preview
                         document.getElementById('model-length').value = model.materialUsed;
                    };
                };
                row.onclick = createClickHandler(row);
        }
        oldTableBody.parentNode.replaceChild(newTableBody, oldTableBody);
        document.getElementById('loading1').hidden=true;
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

    async printButton() {
           await this.printModel();
           alert("Model printed!");
    }

    async printModel() {
        const filamentId = localStorage.getItem("id");
        const material = localStorage.getItem("material");
        const length = (localStorage.getItem("weight") - document.getElementById('model-length').value);
        const isActive = localStorage.getItem("active");
        const color = localStorage.getItem("color");

        await this.filamentsClient.editFilament(filamentId, material, length, isActive, color);
    }

    async addModel() {
        const link = document.getElementById('add-model-link').value;
        const linkRemaining = document.getElementById('add-model-length').value;
        const isActive = document.getElementById('add-isActive').value;
        const keyword = document.getElementById('add-model-keyword').value;

            await this.modelsClient.addModel(link, linkRemaining, isActive, keyword)

    }

    async deleteButton() {
        if(document.getElementById('model-id').value == "") {
            alert("Error: please fill out all fields!")
            return;
        } else {
           await this.deleteModel();
           alert("Model deleted!");
        }
    }

    async deleteModel() {
        const keyword = new URLSearchParams(window.location.search).get('keyword');
        const modelId = document.getElementById('model-id').value;
        await this.modelsClient.deleteModel(keyword, modelId);
    }



}
const main = async () => {
    const manageModels = new ManageModels();
    manageModels.mount();
};

window.addEventListener('DOMContentLoaded', main);