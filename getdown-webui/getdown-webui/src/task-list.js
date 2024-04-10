import {LitElement, html, css} from 'lit';
import '@vaadin/grid';
import '@vaadin/grid/vaadin-grid-column-group.js';
import '@vaadin/grid/vaadin-grid-filter-column.js';
import '@vaadin/grid/vaadin-grid-selection-column.js';
import '@vaadin/grid/vaadin-grid-sort-column.js';
import '@vaadin/grid/vaadin-grid-tree-column.js';
import '@vaadin/polymer-legacy-adapter/template-renderer.js';

export class TaskList extends LitElement {
  /**
   * Object describing property-related metadata used by LitElement to observe
   * changes in properties and render the template based on state.
   */
  static get properties() {
    return {
      tasks:{type: Array,value: []}
    };
  }

  // Initialize properties
  constructor() {
    super();
  }

  async loadTasks() {
    const tasksJson = await fetch("https://getdown.local.lumue.net/api/tasks")
      .then((r)=>r.json())
      .then((rj)=>rj._embedded.downloadTaskList);

    const grid = this.shadowRoot.getElementById('grid');

    grid.items=tasksJson;
  }
  static get styles() {

    return css`
      :host {
        display: block;

        padding: 10px;
      }

      .toolbar {
        display: flex;
        flex-direction: row-reverse
      }

      vaadin-dialog[part="content"] {
        width: 800px;
      }

      vaadin-grid-selection-column[part="label"] {
        display: none;
      }

      vaadin-grid#grid {
        font-size: 9pt !important;
      }

    `;
  }


  connectedCallback() {
    super.connectedCallback()
    this.loadTasks()
  }

  render() {
    return html`
        <div class="toolbar">
          <paper-icon-button icon="add" on-tap="_handleAdd"></paper-icon-button>
          <paper-icon-button icon="delete" on-tap="_handleDelete" hidden$="[[hideDeleteButton]]"></paper-icon-button>
          <paper-icon-button icon="file-download" on-tap="_handleDownload"
                             hidden$="[[hideDownloadButton]]"></paper-icon-button>
        </div>

        <vaadin-grid id="grid"
                     theme="compact"
                     size="20"
                     column-reordering-allowed column-resizing multi-sort>
          <vaadin-grid-selection-column width="30px" flex-grow="0" auto-select></vaadin-grid-selection-column>
          <vaadin-grid-column width="60px" flex-grow="0" align="center">
            <template class="header">
              <vaadin-grid-sorter path="state">State</vaadin-grid-sorter>
            </template>
            <template>
              <div>
                <iron-icon id="stateicon" icon="state:dot" role="img" aria-label="[[item.state]]"
                           style$="color: [[_stateColor(item.state)]];"></iron-icon>
                <paper-tooltip for="stateicon">[[item.state]]</paper-tooltip>
              </div>
            </template>
          </vaadin-grid-column>
          <vaadin-grid-column resizable>
            <template class="header">
              <vaadin-grid-sorter path="sourceUrl">Url</vaadin-grid-sorter>
            </template>
            <template>[[item.sourceUrl]]</template>
          </vaadin-grid-column>
          <vaadin-grid-column resizable>
            <template class="header">
              <vaadin-grid-sorter path="name">Name</vaadin-grid-sorter>
            </template>
            <template>[[item.name]]</template>
          </vaadin-grid-column>
          <vaadin-grid-column width="60px" flex-grow="0">
            <template class="header">
              <vaadin-grid-sorter path="expectedSize">Size</vaadin-grid-sorter>
            </template>
            <template>[[_formatBytes(item.expectedSize,1)]]</template>
          </vaadin-grid-column>
          <vaadin-grid-column width="100px" flex-grow="0">
            <template class="header">
              <vaadin-grid-sorter path="creationTime">Added</vaadin-grid-sorter>
            </template>
            <template>[[_formatDateTime(item.creationTime)]]</template>
          </vaadin-grid-column>
        </vaadin-grid>
    `;
  }

}

// Register the element with the browser
customElements.define('task-list', TaskList);
