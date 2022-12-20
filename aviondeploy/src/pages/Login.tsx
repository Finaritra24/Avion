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
  UseIonRouterResult,
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
import axios from "axios";
import { useRef } from "react";

import Token from "../VehiculeManage/Token";
import { useIonRouter } from "@ionic/react";




function sendingAPI(mdp :string,router:UseIonRouterResult){  
    const api=axios.create(
        {
          baseURL:"http://172.16.0.174:4091", 
        }
    );
    api.post("/signin",{ mdp : mdp}).then( (res)=>{
        const data  = res.data["data"];
        const token:Token={token:"Bearer "+data.token};
        window.localStorage.setItem("token",token.token);
        router.push("/list");
      }).catch(err=>(alert(err)));
};

const Login : React.FC = () => {
    const router = useIonRouter(); 
    const motdepasseRef=useRef<HTMLIonInputElement>(null);
    const log=() =>{
        const mdp  = motdepasseRef.current!.value!+"";
        sendingAPI(mdp,router)
    }
    return(
    <IonContent className="ion-padding">
        <IonGrid>
        <IonRow>
            <IonCol>
            <IonItem>
                <IonLabel position="floating"> Mot de passe </IonLabel>
                <IonInput ref={motdepasseRef} value="tp2022"></IonInput>
            </IonItem>
            </IonCol>
        </IonRow>
        <IonRow>
            <IonCol className="ion-text-center">
            <IonButton onClick={log}>se connecter</IonButton>
            </IonCol>
        </IonRow>
        </IonGrid>
    </IonContent>
  );
};
export default Login;

