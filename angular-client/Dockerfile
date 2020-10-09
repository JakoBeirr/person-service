FROM node:alpine as builder
RUN apk update && apk add --no-cache make git
WORKDIR /app
COPY package.json package-lock.json  /app/
RUN npm install @angular/cli -g
RUN cd /app && npm install
COPY . /app
RUN cd /app && npm run build

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist/angular-client /usr/share/nginx/html
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
