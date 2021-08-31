import ReactDOM from './js/react-dom.js';
import html from './js/htm.js';
import App from "./App.js"

ReactDOM.render(html`
    <${React.StrictMode}>
        <${App}>
    <//>
    `, document.body);