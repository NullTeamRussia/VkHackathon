<template>
  <div id="hermitage_panel_nodes">
    <div class="canvas_container">
      <canvas id="nodes_graphics"></canvas>
    </div>
    <div class="node__panel">
      <div class="node__actions">
        <div @click="activateNodeAddingMode">
          <icon 
            name="plus" 
            scale="1.5"
            :class="{'node__action--active': canvas_active_mode === 'node_adding'}"></icon>
        </div>
        <div @click="activateViewMode">
          <icon 
            name="arrows" 
            scale="1.3"
            :class="{'node__action--active': canvas_active_mode === 'view'}"></icon>
        </div>
        <div @click="zoomIn">
          <icon 
            name="search-plus" 
            scale="1.4">
          </icon>
        </div>
         <div @click="zoomOut">
          <icon 
            name="search-minus" 
            scale="1.4">
          </icon>
        </div>
      </div>
      <div class="node_info"></div>
    </div>
  </div>
</template>

<script>
    import {fabric} from 'fabric'
    import 'vue-awesome/icons/plus'
    import 'vue-awesome/icons/search-minus'
    import 'vue-awesome/icons/search-plus'
    import 'vue-awesome/icons/arrows'
    export default {
      name: 'HermitagePanelNodes',
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
          nodes_graphics: '',
          canvas_moving: false,
          canvas_moving_allowed: true,
          canvas_active_mode: ''
        }
      },
      mounted () {
        this.nodes_graphics = new fabric.Canvas('nodes_graphics',
          {
            backgroundColor: '#c5d2da',
            selection: false,
            height: window.innerHeight,
            width: window.innerWidth
          }
        )
        this.nodes_graphics.setBackgroundImage('http://www.hermitagemuseum.org/images/woa/TP/TP_B10_F1_1/TP_B10_F1_1.130179108052014.jpg')
        this.activateViewMode()
      },
      methods: {
        activateViewMode () {
          if (this.canvas_active_mode !== 'view') {
            this.canvas_active_mode = 'view'
            this.canvas_moving_allowed = 'true'
            this.nodes_graphics.defaultCursor = 'move'
            this.nodes_graphics.on('mouse:up', this.stopMoveCanvas)
            this.nodes_graphics.on('mouse:down', this.startMoveCanvas)
            this.nodes_graphics.on('mouse:move', this.moveCanvas)
          }
        },
        activateNodeAddingMode () {
          if (this.canvas_active_mode !== 'node_adding') {
            this.canvas_active_mode = 'node_adding'
            this.nodes_graphics.defaultCursor = 'crosshair'
            this.nodes_graphics.on('mouse:down', this.addNode)
          }
        },
        addNode (e) {
          if (this.canvas_active_mode === 'node_adding') {
            var newNode = new fabric.Circle({
              selectable: false
            })
            var point = this.nodes_graphics.getPointer(e.e)
            newNode.setPositionByOrigin(new fabric.Point(point.x, point.y))
            newNode.setRadius(5)
            newNode.setColor('#e74c3c')
            this.nodes_graphics.add(newNode)
            this.activateViewMode()
          }
        },
        startMoveCanvas (e) {
          if (this.canvas_moving_allowed) {
            this.canvas_moving = true
          }
        },
        stopMoveCanvas (e) {
          if (this.canvas_moving_allowed) {
            this.canvas_moving = false
          }
        },
        moveCanvas (e) {
          if (this.canvas_moving && e && e.e) {
            var delta = new fabric.Point(e.e.movementX, e.e.movementY)
            this.nodes_graphics.relativePan(delta)
          }
        },
        zoomIn (e) {
          this.nodes_graphics.setZoom(this.nodes_graphics.getZoom() + 0.1)
        },
        zoomOut (e) {
          this.nodes_graphics.setZoom(this.nodes_graphics.getZoom() - 0.1)
        }
      }
    }
</script>

<style scoped>

  #hermitage_panel_nodes {
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
    box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
    overflow: hidden;
  }

  .node__panel {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: stretch;
    min-width: 300px;
  }

  .node__actions {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    margin-bottom: 50px;
    min-height: 50px;
    background: #fff;
    box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
  }

  .node__actions > div {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 0 10px;
    height: 100%;
    color: #3498db;
    border-left: 0.5px solid rgba(0, 0, 0, 0.3);
    cursor: pointer;
    transition: all 0.3s ease-in-out;
  }

  .node__actions > div:first-child {
    padding-left: 20px;
    border: none; 
  }

  .node__actions > div:hover { color: #2980b9; }

  .node__actions svg.node__action--active {
    color: #2980b9;
  }

  .node_info {
    flex-grow: 1;
    background: #fff;
    box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
  }

</style>
