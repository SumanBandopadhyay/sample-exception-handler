# sample-exception-handler

###Features
- Exception Handling
- Kubernetes
- Actuator

### Kubernetes
* $ kubectl create deployment sample-exception-handler --image=sumanbando/sample-exception-handler --dry-run=client -o=yaml > sample-exception-handler-deployment.yaml
* $ echo --- >> sample-exception-handler-deployment.yaml
* $ kubectl create service clusterip sample-exception-handler --tcp=9001:9001 --dry-run=client -o=yaml >> sample-exception-handler-deployment.yaml

The yaml deployment file will be created.

#### Start the deployment
* kubectl apply -f sample-exception-handler-deployment.yaml
* kubectl get all
* kubectl port-forward service/sample-exception-handler 9001:9001
