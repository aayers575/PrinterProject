import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";
/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class ModelsClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'editModel', 'addModel', 'deleteModel', 'getSingleModel', 'getMultipleModels'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }


    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------

    async editModel(modelId, preview, materialUsed, isActive, keyword, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can edit models");
            const response = await this.axiosClient.put(`models/${modelId}`, {
                            modelId: modelId,
                            preview: preview,
                            materialUsed: materialUsed,
                            isActive: isActive,
                            keyword: keyword
                        }, {
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        });

          
            return response.data.model;


        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

        async addModel(preview, materialUsed, isActive, keyword, errorCallback) {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can add models");
                const response = await this.axiosClient.post(`models`, {
                                preview: preview,
                                materialUsed: materialUsed,
                                isActive: isActive,
                                keyword: keyword
                            }, {
                                headers: {
                                    Authorization: `Bearer ${token}`
                                }
                            });

                return response.data.model;


            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

    async deleteModel(orgId, modelId, preview, materialUsed, isActive, keyword, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can edit models");
            const response = await this.axiosClient.delete(`models/${modelId}`, {
                            orgId: orgId,
                            modelId: modelId,
                        }, {
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        });
            return response.data.model;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getSingleModel(orgId, modelId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Encountered token error trying to call Task endpoint.");
            const response = await this.axiosClient.get(`models/${modelId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }});
            return response.data.model;

        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getMultipleModels(keyword, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Encountered token error trying to call Model endpoint.");
            const response = await this.axiosClient.get(`keywords/${keyword}/models`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }});
            return response.data.models;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}