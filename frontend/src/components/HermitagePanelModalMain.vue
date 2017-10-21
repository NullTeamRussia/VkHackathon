<template>
  <div id="hermitage_panel_modal_main">
      <component 
        :is="active_page"
        :nodes.sync="nodes"
        :edges.sync="edges"
        :paths.sync="paths"
        :exponats.sync="exponats"
        :news.sync="news"
        :is_modal_active.sync="is_modal_active">
       </component>
  </div>
</template>

<script>
    import HermitagePanelModalNews from '@/components/modals/HermitagePanelModalNews'
    export default {
      name: 'HermitagePanelModalMain',
      props: [
        'active_page',
        'news',
        'paths',
        'exponats',
        'edges',
        'nodes',
        'is_modal_active'
      ],
      data () {
        return {
        }
      },
      components: {
        'hermitage_panel_news': HermitagePanelModalNews
      }
    }
</script>

<style scoped>

    #hermitage_panel_modal_main {
        z-index: 100;
        position: fixed;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background: rgba(0, 0, 0, 0.3)
    }

</style>
