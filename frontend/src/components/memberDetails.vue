<template>
    <div class="col-xs-12">
        <h2>Member Details</h2>
        <div class="row memberDetails">
            <div class="col-xs-12 col-sm-3 text-center">
                <img class="img-responsive" :src="computeImage(memberData.url)">
                <h3>{{memberData.name}}</h3>
                <router-link to='/' exact>
                    <i class="fas fa-angle-left"></i>
                    Back
                </router-link>
            </div>
            <div class="col-xs-12 col-sm-9 ">
                <span><b>Company:</b></span><span>{{memberData.company}}</span>
                <br />
                <span><b>Project:</b></span><span>{{memberData.project}}</span>
                <br />
                <span><b>Technology:</b></span><span>{{memberData.technology}}</span>
            </div>
        </div>
    </div>
</template>
<script>

 // import axios from 'axios'
  import {AXIOS} from './http-common'

    export default {
        data() {
            return {
                id: this.$route.params.id,
                memberData: {}
            }
        },
        created: function() {
            var headers = {
                'Content-Type': 'application/json'
            }
            //http://10.11.35.136:8019/api/v1/employee/
            AXIOS.get('/v1/employee/' + this.id, {
                    headers: headers
                })
                .then((response) => {
                console.log(response)
                    this.memberData = response.data;
                })
        },
        methods: {
            computeImage(url) {
                return require('../assets/' + url);
            }
        }

    }

</script>
<style scoped="true">
    .memberDetails {
        display: flex;
        align-items: center;
    }

    h3 {
        color: orange;
        margin-top: 5px;
    }

</style>
