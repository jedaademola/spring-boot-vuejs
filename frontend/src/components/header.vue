<template>
    <header class='header'>
        <div class="col-xs-9">
            {{title}}
        </div>
        <div class="col-xs-3">
            <img class="logo" src="../assets/tax-bulb.png">
        </div>
    </header>
</template>
<script>
    export default{
        data(){
            return{
                title:"Sample POC on Microservices"
            }
        }
    }
</script>
<style>
    .header{
        color:#153259;
        background: #f3f3f3;
        padding: 3%;
        font-size: 2em; 
        text-align: center;
        display: flex;
        align-items: center;
    }
    .logo{
        max-width: 50px;
    }
</style>