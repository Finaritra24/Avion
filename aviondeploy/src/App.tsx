import { Redirect, Route } from "react-router-dom";
import {
  IonApp,
  IonRouterOutlet,
  IonHeader,
  IonContent,
  setupIonicReact,
  IonToolbar,
  IonTitle,
  IonGrid,
  IonCol,
  IonItem,
  IonInput,
  IonLabel,
  IonRow,
  IonButton,
} from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";

/* Core CSS required for Ionic components to work properly */
import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";

/* Optional CSS utils that can be commented out */
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";

/* Theme variables */
import "./theme/variables.css";
import React from "react";
import Login from "./pages/Login";
import FicheAvion from "./pages/FicheAvion";
import ListAvion from "./pages/ListAvion";

setupIonicReact();

const App: React.FC = () => {
  return (
    <IonApp>
      <IonReactRouter  >
        <IonRouterOutlet >
          
          <Route exact path="/" component={Login}>
            <Login />
          </Route>

          <Route exact path="/list" component={ListAvion}>
          </Route>

          <Route exact path="/fiche/:id" component={FicheAvion}>
          </Route>
          
        </IonRouterOutlet>
      </IonReactRouter>
    </IonApp>
  );
};

export default App;
