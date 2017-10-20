<template>
  <div id="hermitage_panel_main">
      <div class="sidebar">
        <div class="brand">
            <span class="brand-text">Hermitage</span>
        </div>
        <div class="sidebar__menu">
            <div class="menu__item"
                @click="active_page = 'hemitage_panel_main'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_main'}">
                <icon name="map-o" scale="1" class="menu__item-icon"></icon>    
                <span class="menu__item-text">Павильоны</span>
            </div>
        </div>   
      </div>
      <div class="container">
          <div class="header">
          </div>
          <!-- <component :is="active_page"></component> -->
      </div>
  </div>
</template>

<script>
    import 'vue-awesome/icons/map-o'
    export default {
      name: 'HermitagePanelMain',
      data () {
        return {
          active_page: 'hermitage_panel_main'
        }
      }
    }
</script>

<style scoped>

    #hermitage_panel_main {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: stretch;
        height: 100vh;
        width: 100vw;
        overflow: hidden;
    }

    .sidebar {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        width: 200px;
        background: #fff;
        box-shadow: 0.5px 0 0 0.5px rgba(0, 0, 0, 0.3); 
    }

    .container {
        flex-grow: 1;
    }

    .brand {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin-bottom: 50px;
        height: 50px;
        box-shadow: 0 0.5px 0 0.5px rgba(0, 0, 0, 0.3);
    }

    .brand-text {
        color: #2ecc71;
        font-size: 32px;
        font-weight: 800;
    }

    .menu__item {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin-left: 5%;
        height: 50px;
        width: 95%;
        color: #2ecc71;
        transition: all 0.3s ease-in-out;
    }

    .menu__item:hover { cursor: pointer; }
    .menu__item:not(.menu__item--active) { 
        background: #2ecc71;
        color: #fff;
    }

    .menu__item-icon {
        margin-top: 3px;
        margin-right: 7px;
    }

    .menu__item-text {
        font-size: 24px;
    }

    .menu__item--active {
        color: #fff;
        background: #2ecc71;
    }

    .menu__item--active::after {
        position: absolute;
        content: '';
        background: #27ae60;
        height: 50px;
        width: 10px;
        left: 190px;
    }

</style>


