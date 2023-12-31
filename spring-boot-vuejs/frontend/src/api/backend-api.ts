import axios, {AxiosResponse} from 'axios'
import usersList from "@/components/UsersList.vue";
import {RouteParamValue} from "vue-router";

export const axiosApi = axios.create({
    baseURL: `/api`,
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' }
});

interface User {
    username: string,
    firstName: string,
    lastName: string,
    birthday: string,
    profilePicture: string
    gender: string,
    userType: string,
    accountType: string,
}

export default {

    login(username: string, password: string): Promise<AxiosResponse<User>> {
        const userDTO = {
            username: username,
            password: password
        }
        return axiosApi.post("authentication/login", userDTO)
    },
    // registerUser(username: string, password: string, email: string, firstName: string, lastName: string): Promise<AxiosResponse<User>> {
    //
    // },
    fetchUser(username: string | RouteParamValue[]): Promise<AxiosResponse<User>> {
        return axiosApi.get("/user/get-user/" + username)
    },
    fetchUsers(): Promise<AxiosResponse<Array<User>>> {
        return axiosApi.get("user/get-all")
    },
    // hello(): Promise<AxiosResponse<string>> {
    //     return axiosApi.get(`/hello`);
    // },
    // // getUser(userId: number): Promise<AxiosResponse<User>> {
    // //     return axiosApi.get(`/user/` + userId);
    // // },
    // createUser(firstName: string, lastName: string): Promise<AxiosResponse<number>> {
    //     return axiosApi.post(`/user/` + firstName + '/' + lastName);
    // },
    // getSecured(user: string, password: string): Promise<AxiosResponse<string>> {
    //     return axiosApi.get(`/secured/`,{
    //         auth: {
    //             username: user,
    //             password: password
    //         }});
    // }
}


