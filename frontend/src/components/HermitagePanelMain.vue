<template>
  <div id="hermitage_panel_main">
      <div class="sidebar">
        <div class="brand">
        </div>
        <div class="sidebar__menu">
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_routes'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_routes'}">
                <icon name="map-o" scale="1" class="menu__item-icon"></icon>
            </div>
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_nodes'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_nodes'}">
                <icon name="map-marker" scale="1" class="menu__item-icon"></icon>    
            </div>
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_exponats'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_exponats'}">
                <icon name="picture-o" scale="1" class="menu__item-icon"></icon>
            </div>
            <div class="menu__item"
                @click="active_page = 'hermitage_panel_news'"
                :class="{'menu__item--active': active_page === 'hermitage_panel_news'}">
                <icon name="newspaper-o" scale="1" class="menu__item-icon"></icon>
            </div>
        </div>   
      </div>
      <div class="container">
          <div class="header">
              <div class="header-page">
                  <span class="header-page__text">{{page_names[active_page]}}</span>
              </div>
          </div>
          <component :is="active_page" class="container__content"></component>
      </div>
  </div>
</template>

<script>
    import 'vue-awesome/icons/map-o'
    import 'vue-awesome/icons/picture-o'
    import 'vue-awesome/icons/newspaper-o'
    import 'vue-awesome/icons/map-marker'
    import HermitagePanelRoutes from '@/components/panel_components/HermitagePanelRoutes'
    import HermitagePanelExponats from '@/components/panel_components/HermitagePanelExponats'
    import HermitagePanelNodes from '@/components/panel_components/HermitagePanelNodes'
    import HermitagePanelNews from '@/components/panel_components/HermitagePanelNews'
    export default {
      name: 'HermitagePanelMain',
      data () {
        return {
          page_names: {
            'hermitage_panel_routes': 'маршруты',
            'hermitage_panel_nodes': 'павильоны',
            'hermitage_panel_exponats': 'экспонаты',
            'hermitage_panel_news': 'новости'
          },
          active_page: 'hermitage_panel_routes'
        }
      },
      components: {
        'hermitage_panel_routes': HermitagePanelRoutes,
        'hermitage_panel_nodes': HermitagePanelNodes,
        'hermitage_panel_exponats': HermitagePanelExponats,
        'hermitage_panel_news': HermitagePanelNews
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
        z-index: 11;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        width: 50px;
        background: #fff;
        box-shadow: 0.5px 0 1px 0 rgba(50, 50, 50, 0.3);
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
        color: #3498db;
        transition: all 0.3s ease-in-out;
    }

    .menu__item:hover { cursor: pointer; }

    .menu__item:not(.menu__item--active):hover { 
        background: #3498db;
        color: #fff;
    }

    .menu__item--active {
        color: #fff;
        background: #3498db;
    }

    .menu__item--active::after {
        position: absolute;
        content: '';
        background: #2980b9;
        height: 50px;
        width: 5px;
        left: 45px;
    }

    .container {
        z-index: 10;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: stretch;
        flex-grow: 1;
    }

    .header {
        z-index: 10;
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
        color: #3498db;
    }

    .container__content {
        z-index: 9;
        flex-grow: 1;
        padding: 50px;
        background: #ecf0f1;
    }

</style>


