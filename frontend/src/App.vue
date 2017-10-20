<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>

  * {
    font-family: 'Open Sans', sans-serif;
  }

  body {
    padding: 0;
    margin: 0;
  }

  #app {
    min-height: 100vh;
    min-width: 100vw;
  }

</style>
