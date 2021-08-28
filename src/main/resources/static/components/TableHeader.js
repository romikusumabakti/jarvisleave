import {html} from '../js/standalone.module.js';

function TableHeader(props) {

    return html`
        <thead>
        <tr class="mdc-data-table__header-row">
            ${props.children}
        </tr>
        </thead>
    `;
}

export default TableHeader;