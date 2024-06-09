
import OrganizationClient from '../api/organizationClient';
import UserRoleClient from '../api/userRoleClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const DISPLAY_ROLES_KEY = 'display-roles-list';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: '',
    [DISPLAY_ROLES_KEY]: [],
};


/**
 * Logic needed for the view playlist page of the website.
 */
class LandingPageScripts extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'startupActivities', 'populateDropdown', 'changeButtonTarget'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        console.log("landingPageScripts constructor");
    }


    mount() {

        this.header.addHeaderToPage();
        this.organizationClient = new OrganizationClient();
        this.userRoleClient = new UserRoleClient();
        this.startupActivities();
    }

    async startupActivities() {
        //If user is logged in when app starts, this method will initialize page elements
        if (await this.organizationClient.verifyLogin()) {
            const{email, name} = await this.organizationClient.getIdentity().then(result => result);
            this.dataStore.set([COGNITO_EMAIL_KEY], email);
            this.dataStore.set([COGNITO_NAME_KEY], name);
            const displayRoles = await this.userRoleClient.getDisplayRolesForUser(email);
            this.dataStore.set([DISPLAY_ROLES_KEY], displayRoles);
            this.populateDropdown(displayRoles);
            document.getElementById('title').innerText = `Hello ${name}. Let's get to work!`;
            document.getElementById('navigate-btn').hidden = false;
            document.getElementById('new-role-btn').hidden = false;
            document.getElementById('userRoles').addEventListener('change', this.changeButtonTarget)
        } else {
            document.getElementById('title').innerText = `Welcome to Printer Management Console. Please log-in at the top right to continue.`;
        }
    }

    populateDropdown(displayRoles) {
        const select = document.getElementById('userRoles');

        for (const displayRole of displayRoles) {
            if (displayRole.roleStatus != "Inactive") {
                var opt = document.createElement('option');
                opt.innerText = displayRole.orgDisplayName.concat(" ~ ", displayRole.jobRole);
                if (displayRole.roleStatus == 'Pending') {
                    opt.innerText = displayRole.orgDisplayName.concat(" ~ ", displayRole.jobRole).concat("  PENDING APPROVAL");
                    opt.disabled = 'disabled';
                }
                select.appendChild(opt);
            }
        }
        document.getElementById('userRoles').hidden = false;
    }

    changeButtonTarget() {
        const button = document.getElementById('navigate-btn');
        const displayRole = this.dataStore.get(DISPLAY_ROLES_KEY)[document.getElementById('userRoles').selectedIndex -1];
        if(displayRole.jobRole == 'Manager') {
            button.setAttribute('href', 'projectsList.html?orgId=' + displayRole.orgId);
        }

        if(displayRole.jobRole == 'Worker') {
            button.setAttribute('href', 'assignedTaskList.html?orgId=' + displayRole.orgId);
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
