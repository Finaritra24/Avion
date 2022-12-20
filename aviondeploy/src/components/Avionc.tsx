import { IonContent } from '@ionic/react';
import { Avion } from '../modele/Avion';
import './Avion.css';


const Avionc: React.FC<any> = (avion) => {
    return (
            <tr>
                <td>{avion.idAvion}</td>
                <td>{avion.modele}</td>
                <td>{avion.image}</td>
            </tr>
    );
};

export default Avionc;

