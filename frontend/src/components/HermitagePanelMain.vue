<template>
  <div id="hermitage_panel_main">
      <div class="sidebar">
        <div class="brand">
        </div>
        <div class="sidebar__menu">
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_dashboard'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_dashboard'}">
                <icon name="dashboard" scale="1" class="menu__item-icon"></icon>
            </div>
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_nodes'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_nodes'}">
                <icon name="map-o" scale="1" class="menu__item-icon"></icon>    
            </div>
             <div class="menu__item"
                @click="active_page = 'hermitage_panel_exponats'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_exponats'}">
                <icon name="picture-o" scale="1" class="menu__item-icon"></icon>
            </div>
        </div>   
      </div>
      <div class="container">
          <div class="header">
              <div class="header-page">
                  <span class="header-page__text">{{page_names[active_page]}}</span>
              </div>
          </div>
          <!-- <component :is="active_page"></component> -->
      </div>
  </div>
</template>

<script>
    import 'vue-awesome/icons/map-o'
    import 'vue-awesome/icons/picture-o'
    import 'vue-awesome/icons/dashboard'
    export default {
      name: 'HermitagePanelMain',
      data () {
        return {
          page_names: {
            'hermitage_panel_dashboard': 'статистика',
            'hermitage_panel_nodes': 'павильоны',
            'hermitage_panel_exponats': 'экспонаты'
          },
          active_page: 'hermitage_panel_dashboard'
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
        width: 50px;
        background: #fff;
        box-shadow: 0.5px 0 1px 0 rgba(50, 50, 50, 0.3);
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
        box-shadow: 0 0.5px 1px 0 rgba(50, 50, 50, 0.3);
    }

    .menu__item {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        height: 50px;
        color: #2ecc71;
        transition: all 0.3s ease-in-out;
    }

    .menu__item:hover { cursor: pointer; }

    .menu__item:not(.menu__item--active):hover { 
        background: #2ecc71;
        color: #fff;
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
        width: 5px;
        left: 45px;
    }

    .container {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
    }

    .header {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: stretch;
        height: 50px;
        box-shadow: 0 0.5px 1px 0 rgba(50, 50, 50, 0.3);
    }

    .header-page {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin-left: 20px;
    }

    .header-page__text {
        text-transform: capitalize;
        font-size: 24px;
        color: #2ecc71;
    }

</style>


