import { LitElement, html, css } from 'lit';
import './download-view.js';

const logo = new URL('../assets/icon-getdown.svg', import.meta.url).href;

class GetdownWebui extends LitElement {
  static properties = {
    header: { type: String },
  }

  static styles = css`


   :host{
      min-height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      font-size: calc(10px + 2vmin);
      color: #1a2b42;
      margin: 0 auto;
      text-align: center;
    }

    .card {
      margin: 24px;
      padding: 16px;
      color: #757575;
      border-radius: 5px;
      background-color: #fff;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
    }
    .app-footer a {
      margin-left: 5px;
    }
  `;

  constructor() {
    super();
    this.header = 'My app';
  }

  render() {
    return html`
      <download-view class="card"></download-view>
    `;
  }
}

customElements.define('getdown-webui', GetdownWebui);
