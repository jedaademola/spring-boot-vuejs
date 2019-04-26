<template>
    <div class="col-xs-12">
        <h2>Employee Dashboard</h2>
        <div class="row addNewMemeber">
            <div class="col-xs-6">
                <router-link to="/addMember">
                    <i class="fas fa-plus"></i>
                    Add New Member
                </router-link>
            </div>
            <div class="col-xs-6 text-right">
                <span class="totalCount">Total Employees:</span> 
                <span class="badge">
                    {{this.members.length}}
                </span>
            </div>

        </div>
        <div class="members col-xs-12">
            <div class="col-xs-12 col-sm-4 member" v-for="member in members" :key="member.id">
                <router-link :to="{ path: 'view/'+member.id}" exact>
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <img class="img-responsive" :src="computeImage(member.url)" v-bind:alt="member.id">

                            <a href="#" class="btn btn-danger" v-on:click.prevent="RemoveEmployee(member.id)">
                                <i class="far fa-trash-alt"></i>
                                Remove
                            </a>
                            <br />
                            <span class="memberName">{{member.name}}</span><br />
                            (<span>{{member.project}}</span>)
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                members: []
            }
        },
        created: function() { // first life cycle hook
            console.log("Created:: life cycle hook called to load employees");
            this.loadEmployees();
        },
        methods: {
            computeImage(url) {
                return require('../assets/' + url);
            },
            RemoveEmployee(id) {
                //event.preventDefault();
                var headers = {
                    'Content-Type': 'application/json'
                }

                //http://10.11.35.136:8019/api/v1/employee
                this.axios.delete('http://10.11.35.144:8019/api/v1/employee/delete/' + id, {
                        headers: headers
                    })
                    .then((response) => {
                        console.log(response)
                        this.loadEmployees();
                    })

            },
            loadEmployees() {

                var headers = {
                    'Content-Type': 'application/json'
                }

                //http://10.11.35.136:8019/api/v1/employee
                this.axios.get('http://10.11.35.144:8019/api/v1/employee', {
                        headers: headers
                    })
                    .then((response) => {
                        console.log(response)
                        this.members = response.data;
                    })
            }
        }
    }

</script>
<style>
    h2 {
        text-align: center;
        background: #326fb6;
        color: white;
        padding: 10px;
    }

    .addNewMemeber {
        margin-top: 15px;
    }

    .members {
        max-height: 500px;
        overflow-y: scroll;
    }

    .member {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .memberName {
        font-size: 1.5em;
        color: orange;
    }

    img {
        display: block;
    }
    
    .totalCount{
        color: #409c21
    }
    .badge{
        background: #409c21;
    }

    @media screen and (min-width: 768px) {}

</style>
