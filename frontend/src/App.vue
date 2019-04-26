<template>
  <div id="app" class="row">
    <appHeader></appHeader>
    <router-view/>
    <appFooter></appFooter>
  </div>
</template>

<script>

    import appHeader from './components/header.vue'
    import appFooter from './components/footer.vue'

    import groupMembers from './components/GroupMembers.vue'

    export default {
        name: 'app',
        components:{
            'appHeader':appHeader,
            'appFooter':appFooter,
            'groupMembers':groupMembers
        },
        data () {
            return {
                title: ''
            }
        }
    }
</script>

<style>
  #app{
    max-width: 750px;
    margin:3% auto 0 auto;
  }

</style>
