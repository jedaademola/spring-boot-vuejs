<template>
<div class="col-xs-12">
    <div class="addMemberSection row"> 
        <div class="col-xs-12 text-center">
            <h2>Add New member</h2>
        </div>
        <div class="col-xs-12">
            <router-link to="/" exact>
                <i class="fa fa-home"></i>
                Dashboard
            </router-link>
        </div>
        <div class="col-xs-12"> 
            <div class="alert alert-success displayNone">
              <strong>Success!</strong> Member Added, Click on Dashboard to view changes.
            </div>
        </div>
        <div class="col-xs-5 text-right">
            <label for="memberName">Member Name:</label>
        </div>
        <div class="col-xs-7">
            <input v-model="memberData.name" id="memberName" type="text" placeholder="Enter Name here.."/>
        </div>
        <div class="col-xs-5 text-right">
            <label for="projectName">Project:</label>
        </div>
        <div class="col-xs-7">
            <input v-model="memberData.project" id="projectName" type="text" placeholder="Enter Project here.."/>
        </div>
        <div class="col-xs-5 text-right">
            <label for="technology">Technology:</label>
        </div>
        <div class="col-xs-7">
            <input v-model="memberData.technology" id="technology" type="text" placeholder="Enter Technology here.."/>
        </div>
        <div class="col-xs-7 col-xs-offset-5">
            <button type="button" class="btn btn-success" @click="addMember">Add Member</button>
        </div>
    </div>
    </div>
</template>

<script>
 // import axios from 'axios'
  import {AXIOS} from './http-common'

    export default{
        data(){
            return{
                memberData:{
                    id:Math.floor(Math.random()*90000) + 10000,
                    name:"",
                    url:"iconProfile.png",
                    company:"Deloitte LLP",
                    project:"",
                    technology:""
                }
            }
        },
        methods:{
            addMember(){
            var headers = {
                'Content-Type': 'application/json'
            }
                AXIOS.post("/v1/employee",this.memberData, {
                    headers: headers
                })
                    .then((response)=>{
                    console.log(response); 
                    $(".alert-success").removeClass('displayNone');
                    
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
              })
            }
        }
    }
</script>
<style>
    .displayNone{
        display: none;
    }
    h1 {
        text-align: center;
        background: #326fb6;
        color: white;
        padding: 10px;
    }
    .addMemberSection{
        margin-bottom: 3%;
    }
    .btn{
        margin-top: 3%;
    }
</style>