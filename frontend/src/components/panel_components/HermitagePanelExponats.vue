<template>
  <div id="hermitage_panel_exponats">
  </div>
</template>

<script>
    export default {
      name: 'HermitagePanelExponats',
      props: [
        'news',
        'is_modal_active',
        'paths',
        'edges',
        'exponats',
        'nodes'
      ],
      data () {
        return {
        }
      }
    }
</script>

<style scoped>

  #hermitage_panel_exponats { padding: 50px; }

</style>
