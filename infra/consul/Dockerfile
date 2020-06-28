FROM alpine:3.7

ENV CONSUL_VERSION 1.2.1

RUN mkdir /consul

# download dependencies
RUN apk --no-cache add \
      bash \
      ca-certificates \
      wget

# download and set up consul
RUN wget --quiet --output-document=/tmp/consul.zip https://releases.hashicorp.com/consul/${CONSUL_VERSION}/consul_${CONSUL_VERSION}_linux_amd64.zip && \
      unzip /tmp/consul.zip -d /consul && \
      rm -f /tmp/consul.zip && \
      chmod +x /consul/consul

ENV PATH="PATH=$PATH:$PWD/consul"

COPY ./config/consul-config.json /consul/config/config.json

EXPOSE 8500

ENTRYPOINT ["consul"]