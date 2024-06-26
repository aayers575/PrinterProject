import { Auth } from 'aws-amplify';
import UserRoleClient from '../api/userRoleClient';
import BindingClass from "../util/bindingClass";
import Authenticator from '../api/authenticator';
import DataStore from "../util/DataStore";

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
};

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton', 'createNavButton'
        ];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);

        this.client = new UserRoleClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();
        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const navToFilamentManagement = this.createNavButton("Filament Management", 'filamentManagement.html');
        const navToModelManagement = this.createNavButton("Model Management", 'modelManagement.html');
        
        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        if (await this.authenticator.isUserLoggedIn() == true) {
            header.appendChild(navToFilamentManagement);
            header.appendChild(navToModelManagement);
        }
        header.appendChild(userInfo);
    }

    createNavButton(text, htmlTarget) {
        const button = document.createElement('a');
        button.classList.add('navButton');
        button.href = htmlTarget;
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }



    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'Printer Management';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
