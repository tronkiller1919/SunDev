FROM node:18.2.0-alpine3.14 AS build
WORKDIR /angular-app
COPY . /angular-app
RUN npm install
RUN npm install -g @angular/cli && npm run build --omit=dev



FROM nginx:1.17.1-alpine
COPY --from=build /angular-app/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /angular-app/dist/return-order-managment-portal /usr/share/nginx/html
