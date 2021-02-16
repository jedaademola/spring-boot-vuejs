<template>
    <footer class="col-xs-12">
        {{content}}
    </footer>
</template>
<script>
    export default{
        data(){
            return{
                content:"@ Lukman  LLP"
            }
        }
    }
</script>
<style>
    footer{
        color:#fff;
        background: #153259;
        border-top: 1px solid white;
        padding: 3%;
        margin-top: 3%;
        font-size: 1em;  
        text-align: center;
    }
</style>