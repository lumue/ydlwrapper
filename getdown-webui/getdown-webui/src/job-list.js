import {LitElement, html, css} from 'lit';
import '@vaadin/grid';
import '@vaadin/grid/vaadin-grid-column-group.js';
import '@vaadin/grid/vaadin-grid-filter-column.js';
import '@vaadin/grid/vaadin-grid-selection-column.js';
import '@vaadin/grid/vaadin-grid-sort-column.js';
import '@vaadin/grid/vaadin-grid-tree-column.js';
import '@vaadin/polymer-legacy-adapter/template-renderer.js';

export class JobList extends LitElement {
  static get properties() {
    return {

    }
  }

  connectedCallback() {
    super.connectedCallback()
    this.loadJobs()
  }

  async loadJobs() {

    let items = await fetch("https://getdown.local.lumue.net/api/jobs")
      .then((r)=>r.json())
      .then((r)=>r._embedded.youtubedlDownloadJobList)


    const grid = this.shadowRoot.getElementById('grid');

    grid.items= items
  }

  _toJson(item){
    return JSON.stringify(item)
  }

  _percentage(gw, pw) {
    if(gw===0||!gw ||!pw)
      return 0;
    return Math.round(((pw / gw) * 100) * 100) / 100
  }

  isSelectionEmpty(items) {
    return !items
      ||
      (!items.length > 0)
  }

  _isIndeterminate(state){
    if(state==="RUNNING" || state==="FINISHED")
      return false;
    return true;
  }

  _themeForState(state){
    if(state==="RUNNING" || state==="FINISHED")
      return "success"
    if(state==="ERROR")
      return "error"
    return "contrast"
  }

  _selectedItemsChanged(event) {

    if("selectedItems.length"===event.detail.path)
      return;
    const selectedJobs=this.$.grid.selectedItems;
    console.log('selection changed to '+selectedJobs)
    this.dispatch('setSelectedJobs',selectedJobs);
  }

  _onActiveItemChanged(e) {
    this.$.grid.detailsOpenedItems = [e.detail.value];
  }


  static get styles() {

    return css`
      .toolbar {
        display: flex;
        flex-direction: row-reverse
      }
    `;
  }

  /**
   * Invoked on each update to perform rendering tasks. This method can return a
   * TemplateResult or nothing to not render.
   */
  render() {
    return html`
      <div class="toolbar" style="min-height:40px;">
        <paper-icon-button icon="cancel" on-tap="_handleCancelTap" hidden$="[[_hideCancel]]"></paper-icon-button>
        <paper-icon-button icon="refresh" on-tap="_handleRestartTap" hidden$="[[_hideRefresh]]"></paper-icon-button>
        <paper-icon-button icon="delete" on-tap="_handleDeleteTap" hidden$="[[_hideDelete]]"></paper-icon-button>
      </div>


      <vaadin-grid id="grid"
                   theme="compact"
                   size="40"
                   column-reordering-allowed column-resizing multi-sort>


        <template class="row-details">
          <job-detail job="[[item]]"></job-detail>
        </template>


        <vaadin-grid-selection-column width="30px" flex-grow="0" auto-select>
        </vaadin-grid-selection-column>
        <vaadin-grid-column resizable>
          <template class="header">
            <vaadin-grid-sorter path="name">Name</vaadin-grid-sorter>
          </template>
          <template>[[item.name]]</template>
        </vaadin-grid-column>
        <vaadin-grid-column width="80px" flex-grow="0">
          <template class="header">
            <vaadin-grid-sorter path="state">State</vaadin-grid-sorter>
          </template>
          <template>[[item.state]]</template>
        </vaadin-grid-column>
        <vaadin-grid-column-group>
          <vaadin-grid-column width="100px" flex-grow="0">
            <template class="header">Progress</template>
            <template>
              <vaadin-progress-bar style="width: 100%"
                                   value="[[item.downloadProgress.downloadedSize]]"
                                   max="[[item.downloadProgress.size]]"
                                   min="0"
                                   theme$="[[_themeForState(item.state)]]"
                                   indeterminate$="[[_isIndeterminate(item.state)]]"
                                   id="paper_progress">
              </vaadin-progress-bar>
            </template>
          </vaadin-grid-column>
          <vaadin-grid-column width="40px" align="right" flex-grow="0">
            <template>
              [[_percentage(item.downloadProgress.size,item.downloadProgress.downloadedSize)]]%
            </template>
          </vaadin-grid-column>
        </vaadin-grid-column-group>
        <vaadin-grid-column resizable>
          <template class="header">Last message</template>
          <template>[[item.message]]</template>
        </vaadin-grid-column>
      </vaadin-grid>
    `;
  }
}

// Register the element with the browser
customElements.define('job-list', JobList);
