import {html} from '../js/standalone.module.js';

function TableHeaderCell(props) {

    return html`
        <th class="mdc-data-table__header-cell${props.numeric ? ' mdc-data-table__header-cell--numeric' : ''}" role="columnheader" scope="col">
            ${props.children}
        </th>
    `;
}

export default TableHeaderCell;