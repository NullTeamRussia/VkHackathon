<template>
  <div id="hermitage_panel_routes">
    <div class="canvas_container">
      <canvas id="routes_graphics"></canvas>
    </div>
    <div class="routes__panel"></div>
  </div>
</template>

<script>
    import {fabric} from 'fabric'
    export default {
      name: 'HermitagePanelRoutes',
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
          routes_graphics: ''
        }
      },
      mounted () {
        this.routes_graphics = new fabric.Canvas('routes_graphics',
          {
            backgroundColor: '#c5d2da',
            selection: false,
            height: window.innerHeight,
            width: window.innerWidth
          }
        )
        this.routes_graphics.setBackgroundImage('/static/hermitage_first_stage.jpg', this.routes_graphics.renderAll.bind(this.routes_graphics), {
          originX: 'left',
          originY: 'top'
        })
      }
    }
</script>

<style scoped>

   #hermitage_panel_routes {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: stretch;
    max-width: 90vw;
  }

  .canvas_container {
    flex-grow: 1;
    margin-right: 50px;
    background: transparent;
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 1px -1px rgba(0,0,0,.2), 0 1px 2px 0 rgba(0,0,0,.12);
    overflow: hidden;
  }

  .routes__panel {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: stretch;
    min-width: 300px;
    max-width: 300px;
  }

  .routes__actions {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 50px;
    min-height: 50px;
    background: #fff;
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.14), 0 1px 1px -1px rgba(0,0,0,.2), 0 1px 2px 0 rgba(0,0,0,.12);
    transition: all 0.3s ease-in-out;
  }

</style>
