<template>
  <div id="hermitage_panel_nodes">
    <div class="canvas_container">
      <canvas id="nodes_graphics"></canvas>
    </div>
    <div class="node__panel">
      <div class="node__actions">
        <div @click="activateNodeAddingPanel" 
        :class="{'node__action--active': canvas_active_mode === 'node_adding'}">
          <icon 
            name="plus" 
            scale="1.5"></icon>
        </div>
        <div @click="activateViewMode" 
        :class="{'node__action--active': canvas_active_mode === 'view'}">
          <icon 
            name="arrows" 
            scale="1.3"></icon>
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
        <div @click="deleteNode" v-if="current_point.top">
          <icon 
            name="trash" 
            scale="1.4">
          </icon>
        </div>
        <div @click="activateLinkMode" v-if="current_point.top"
        :class="{'node__action--active': canvas_active_mode === 'nodes_linking'}">
          <icon 
            name="link" 
            scale="1.4">
          </icon>
        </div>
      </div>
      <div class="node_info" v-if="!is_node_adding_panel_active && canvas_active_mode !== 'nodes_linking'">
        <div class="node_info-name">
          <span class="node_info-name__text">{{current_node.name}}</span>
        </div>
        <div class="node_info-description">
          <span class="node_info-description__text">{{current_node.description}}</span>
        </div>
        <div class="node_info-max">
          <span class="node_info-max__text">Вместимость</span>
          <span class="node_info-max__value">{{current_node.maxCount}}</span>
        </div>
        <div class="node_info-available" 
          :class="{'node_info-available--available': current_node.available, 
          'node_info-available--unavailable': !current_node.available}">
          <span class="node_info-available__text" v-if="current_node.available">Доступен для посещения</span>
          <span class="node_info-available__text" v-if="!current_node.available">Недоступен для посещения</span>
        </div>
      </div>
      <div class="node_info node_info--new" v-if="is_node_adding_panel_active && canvas_active_mode !==  'nodes_linking'">
        <div class="node_info-name">
          <input class="node_info-name__text" placeholder="Название..." type="text" v-model="new_node.name"/>
        </div>
        <div class="node_info-description">
          <textarea class="node_info-description__text" v-model="new_node.description" 
          placeholder="Описание..."></textarea>
        </div>
        <div class="node_info-max">
          <span class="node_info-max__text">Вместимость</span>
          <input class="node_info-max__value" type="number" v-model="new_node.maxCount" placeholder="100"/>
        </div>
        <div class="node_info-available" @click="new_node.available = !new_node.available"
          :class="{'node_info-available--available': new_node.available, 
          'node_info-available--unavailable': !new_node.available}">
          <span class="node_info-available__text" v-if="current_node.available">Доступен для посещения</span>
          <span class="node_info-available__text" v-if="!current_node.available">Недоступен для посещения</span>
        </div>
        <div class="node_info-coordinates" @click="activateNodeAddingMode">
          <span class="node_info-coordinates__text">Поставить точку</span>
        </div>
      </div>
      <div class="node_info nodes_linking" v-if="!is_node_adding_panel_active && canvas_active_mode === 'nodes_linking'">
        <div class="node_info-name">
          <span class="node_info-name__text">{{first_linking_node.name}}</span>
        </div>
        <div class="node_info-max">
          <span class="node_info-max__text">Вместимость</span>
          <span class="node_info-max__value">{{first_linking_node.maxCount}}</span>
        </div>
        <div class="node_info-space">
        </div>
        <div class="node_info-name">
          <span class="node_info-name__text">{{second_linking_node.name}}</span>
        </div>
        <div class="node_info-max">
          <span class="node_info-max__text">Вместимость</span>
          <span class="node_info-max__value">{{second_linking_node.maxCount}}</span>
        </div>
        <div class="node_info-space">
        </div>
         <div class="node_info-weight">
          <span class="node_info-weight__text">Время</span>
          <input class="node_info-weight__value" v-model="linking_nodes_weight"/>
        </div>
        <div class="nodes_linking-submit" @click="linkNodes"
        :class="{'submit--blocked': second_linking_node.name === 'Выберите 2-ую точку'}">Соединить павильоны</div>
      </div>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    import {fabric} from 'fabric'
    import 'vue-awesome/icons/plus'
    import 'vue-awesome/icons/search-minus'
    import 'vue-awesome/icons/search-plus'
    import 'vue-awesome/icons/arrows'
    import 'vue-awesome/icons/trash'
    import 'vue-awesome/icons/link'
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
          is_node_adding_panel_active: false,
          nodes_graphics: '',
          canvas_moving: false,
          canvas_moving_allowed: true,
          canvas_active_mode: '',
          current_floor: 1,
          current_point: {},
          current_node: {
            name: '',
            available: false,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          },
          new_node: {
            name: '',
            available: false,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          },
          first_linking_node: {
            name: 'Выберите 1-ую точку',
            available: true,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          },
          second_linking_node: {
            name: 'Выберите 2-ую точку',
            available: true,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          },
          linking_nodes_weight: 0
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
        this.nodes.forEach(node => {
          var newNode = new fabric.Circle({
            selectable: true,
            hoverCursor: 'pointer'
          })
          newNode.setPositionByOrigin(new fabric.Point(node.x, node.y))
          newNode.setRadius(5)
          newNode.setColor('#e74c3c')
          this.nodes_graphics.add(newNode)
        })
        this.edges.forEach(edge => {
          var newLine = new fabric.Line([edge.startX, edge.startY, edge.endX, edge.endY], {
            stroke: '#e74c3c'
          })
          this.nodes_graphics.add(newLine)
        })
        this.nodes_graphics.setBackgroundImage('/static/hermitage_first_stage.jpg', this.nodes_graphics.renderAll.bind(this.nodes_graphics), {
          originX: 'left',
          originY: 'top'
        })
        this.activateViewMode()
      },
      methods: {
        activateViewMode () {
          if (this.canvas_active_mode !== 'view') {
            this.canvas_active_mode = 'view'
            this.canvas_moving_allowed = 'true'
            this.nodes_graphics.defaultCursor = 'move'
            this.is_node_adding_panel_active = false
            this.nodes_graphics.on('mouse:up', this.stopMoveCanvas)
            this.nodes_graphics.on('mouse:down', this.startMoveCanvas)
            this.nodes_graphics.on('mouse:move', this.moveCanvas)
            this.nodes_graphics.on('object:selected', this.nodeSelected)
            this.nodes_graphics.on('selection:cleared', this.resetCurrentNode)
          }
        },
        activateNodeAddingPanel () {
          this.current_node = this.new_node
          this.is_node_adding_panel_active = true
          this.canvas_active_mode = 'node_adding'
        },
        activateNodeAddingMode () {
          if (this.is_node_adding_panel_active) {
            this.nodes_graphics.defaultCursor = 'crosshair'
            this.nodes_graphics.on('mouse:down', this.addNode)
          }
        },
        addNode (e) {
          if (this.canvas_active_mode === 'node_adding') {
            var newNode = new fabric.Circle({
              selectable: true,
              hoverCursor: 'pointer'
            })
            var point = this.nodes_graphics.getPointer(e.e)
            newNode.setPositionByOrigin(new fabric.Point(point.x - 5, point.y - 5))
            newNode.setRadius(5)
            newNode.setColor('#e74c3c')
            this.nodes_graphics.add(newNode)
            this.new_node.x = point.x - 5
            this.new_node.y = point.y - 5
            axios.post('http://api.hermitage.nullteam.info/nodes', this.new_node).then(resp => {
              this.nodes.push(resp.data)
              this.$emit('update:nodes', this.nodes)
              this.resetCurrentNode()
            })
            this.activateViewMode()
            this.nodes_graphics.off('mouse:down', this.addNode)
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
        },
        resetCurrentNode () {
          this.current_node = {
            name: '',
            available: false,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          }
          this.new_node = {
            name: '',
            available: false,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          }
          this.current_point = {}
        },
        nodeSelected (e) {
          this.current_point = e.target
          this.current_node = this.nodes.filter(node => {
            return node.x === e.target.left + 0.5 && node.y === e.target.top + 0.5
          })[0]
          this.activateViewMode()
        },
        deleteNode () {
          var deleteLink = this.current_node._links.self.href
          this.nodes_graphics.remove(this.current_point)
          this.nodes.splice(this.nodes.indexOf(this.current_node))
          axios.delete(deleteLink).then(resp => {
            this.$emit('update:nodes', this.nodes)
          })
          this.resetCurrentNode()
        },
        activateLinkMode () {
          this.canvas_active_mode = 'nodes_linking'
          this.nodes_graphics.off('object:selected', this.nodeSelected)
          this.nodes_graphics.on('object:selected', this.selectSecondLinkingNode)
          this.first_linking_node = this.current_node
          this.nodes_graphics.off('selection:cleared', this.resetCurrentNode)
        },
        resetLinkingNodes () {
          this.first_linking_node = {
            name: 'Выберите 1-ую точку',
            available: true,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          }
          this.second_linking_node = {
            name: 'Выберите 2-ую точку',
            available: true,
            maxCount: 0,
            description: '',
            exponats: [],
            x: 0,
            y: 0
          }
          this.linking_nodes_weight = 0
        },
        selectSecondLinkingNode (e) {
          this.second_linking_node = this.nodes.filter(node => {
            return node.x === e.target.left + 0.5 && node.y === e.target.top + 0.5
          })[0]
          this.nodes_graphics.off('object:selected', this.selectSecondLinkingNode)
        },
        linkNodes () {
          if (this.second_linking_node.name === 'Выберите 2-ую точку') {
            return
          }
          var line = new fabric.Line(
            [
              this.first_linking_node.x + 5,
              this.first_linking_node.y + 5,
              this.second_linking_node.x + 5,
              this.second_linking_node.y + 5
            ], {
              stroke: '#e74c3c'
            }
          )
          this.nodes_graphics.add(line)
          axios.post('http://api.hermitage.nullteam.info/edges', {
            startX: this.first_linking_node.x + 5,
            startY: this.first_linking_node.y + 5,
            endX: this.second_linking_node.x + 5,
            endY: this.second_linking_node.y + 5,
            weight: this.linking_nodes_weight
          }).then(resp => {
            this.edges.push(resp.data)
            this.$emit('update:edges', this.edges)
          })
          this.resetLinkingNodes()
          this.activateViewMode()
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
    max-width: 300px;
  }

  .node__actions {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 50px;
    min-height: 50px;
    background: #fff;
    box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
    transition: all 0.3s ease-in-out;
  }

  .node__actions > div {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 50px;
    color: #3498db;
    border-left: 0.5px solid rgba(0, 0, 0, 0.3);
    cursor: pointer;
    transition: all 0.3s ease-in-out;
  }

  .node__actions > div:first-child {
    border: none; 
  }

  .node__actions > div:hover { 
    color: #fff;
    background: #3498db;
  }

  .node__actions .node__action--active {
    color: #fff;
    background: #2980b9;
    border: none;
  }

  .node_info {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: stretch;
    flex-grow: 1;
    background: #fff;
    box-shadow: 0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 4px 0 rgba(0,0,0,.12);
  }

  .node_info-name {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    min-height: 50px;
    color: #fff; 
    background: #3498db;
    outline: none;
  }

  .node_info-name__text {
    margin: 0 10px;
    font-size: 24px;
    white-space: nowrap;
    overflow: auto;
    outline: none;
  }

  .node_info-name__text::-webkit-scrollbar { width: 0; height: 0; }

  .node_info-description {
    flex-grow: 1;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: stretch;
    border-bottom: 0.5px solid rgba(0, 0, 0, 0.3);
    overflow: hidden;
  }

  .node_info-description__text {
    margin: 10px;
    width: 100%;
    font-size: 16px;
    font-family: 'Source Sans Pro', 'Open Sans', 'sans-selif';
    overflow: auto;
  }

  .node_info-max {
    min-height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }

  .node_info-max__text {
    margin-left: 10px;
  }

  .node_info-max__value {
    margin-right: 10px;
    font-weight: 600;
  }

  .node_info-available {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    min-height: 50px;
  }

  .node_info-available--available {
    color: #fff;
    background: #2ecc71;
  }

  .node_info-available--unavailable {
    color: #fff;
    background: #e74c3c;
  }

  .node_info-available__text {
    font-size: 18px;
  }

  .node_info--new .node_info-name__text {
    background: #3498db;
    color: #fff;
    border: none;
  }

  .node_info--new .node_info-name__text:-moz-placeholder { color: #fff; }
  .node_info--new .node_info-name__text::-webkit-input-placeholder { color: #fff; }
  .node_info--new .node_info-name__text:-ms-input-placeholder { color: #fff; }

  .node_info--new .node_info-available  { cursor: pointer; }
  
  .node_info--new .node_info-max__value {
    border: none;
    text-align: right;
    outline: none;
  }

  .node_info--new .node_info-description__text {
    outline: none;
    word-wrap: break-word;
    border: none;
  }

  .node_info-coordinates {
    min-height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    background: #3498db;
    color: #fff;
    font-size: 18px;
    cursor: pointer;
  }

  .nodes_linking-submit {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    min-height: 50px;
    font-size: 18px;
    background: #3498db;
    color: #fff;
    cursor: pointer;
  }

  .node_info-weight {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    min-height: 50px;
    border-top: 0.5px solid rgba(0, 0, 0, 0.3);
  }

  .node_info-weight__text { 
    margin-left: 10px; 
    white-space: nowrap;
  }

  .node_info-weight__value {
    margin-right: 10px;
    border: none;
    text-align: right;
    outline: none;
    font-weight: 600;
    font-size: 16px;
  }

  .node_info-space {
    min-height: 50px;
    border-top: 0.5px solid rgba(0, 0, 0, 0.3);
    background: #fff; 
  }

  .submit--blocked:hover {
    cursor: not-allowed;
  }

</style>
