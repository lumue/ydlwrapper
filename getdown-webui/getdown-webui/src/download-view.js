import { LitElement, html, css } from 'lit';
import '@vaadin/split-layout/vaadin-split-layout.js';
import './task-list.js'
import './job-list.js'

class DownloadView extends LitElement{



  render() {
    return html`
        <vaadin-split-layout>
            <task-list ></task-list>
            <job-list ></job-list>
        </vaadin-split-layout>` ;
  }
}
customElements.define('download-view', DownloadView);
