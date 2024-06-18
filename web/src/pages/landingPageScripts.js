import { Auth } from 'aws-amplify';
import Authenticator from '../api/authenticator';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
};


/**
 * Logic needed for the view playlist page of the website.
 */
class LandingPageScripts extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'startupActivities'], this);
        this.authenticator = new Authenticator();
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        console.log("landingPageScripts constructor");
    }


    mount() {
        this.header.addHeaderToPage();
        this.startupActivities();
    }

    async startupActivities() {
        //If user is logged in when app starts, this method will initialize page elements
        if (await this.authenticator.isUserLoggedIn() == true) {
            document.getElementById('title').innerText = `Pick an option`;
            document.getElementById('filament-btn').hidden = false;
            document.getElementById('model-btn').hidden = false;
        } else {
            document.getElementById('title').innerText = `Welcome to Printer Management Console. Please log-in at the top right to continue.`;
        }
    }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPageScripts = new LandingPageScripts();
    landingPageScripts.mount();
};

window.addEventListener('DOMContentLoaded', main);
